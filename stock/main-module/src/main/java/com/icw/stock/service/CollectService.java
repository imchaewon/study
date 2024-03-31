package com.icw.stock.service;

import com.icw.stock.component.RedisComponent;
import com.icw.stock.entity.StockCommon;
import com.icw.stock.entity.StockDetail;
import com.icw.stock.enums.Stock;
import com.icw.stock.model.api.ApiTokenReqDto;
import com.icw.stock.model.api.ApiTokenDto;
import com.icw.stock.model.api.StockCommonDto;
import com.icw.stock.model.api.StockDetailDto;
import com.icw.stock.repository.StockCommonRepository;
import com.icw.stock.repository.StockDetailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectService {
	private final StockDetailRepository stockDetailRepository;
	private final StockCommonRepository stockCommonRepository;
	private final RedisComponent redisComponent;
	private final ObjectMapper objectMapper;
	@Value("${appkey}")
	private String appKey;
	@Value("${appsecret}")
	private String appsecret;

	public void collect(String[] itemCode){
		try {
			saveRedis();
		} catch (JsonProcessingException e) {
			System.out.println("CollectService.collect()");
			e.printStackTrace();
		}
		saveCommonInfo(itemCode);
		saveCurrentPrice(itemCode);
	}

	private void saveRedis() throws JsonProcessingException {
		String token = redisComponent.getData(Stock.API_TOKEN.name());
		ApiTokenDto tokenDto;
		if(token == null) {
			tokenDto = getToken();
			redisComponent.setData(Stock.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
		}else{
			ApiTokenDto apiTokenDto = redisComponent.getAndMapToDto(Stock.API_TOKEN.name(), ApiTokenDto.class);
			log.info("apiTokenDto: {}", apiTokenDto);

			String tokenExpired = apiTokenDto.getAccessTokenExpired();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(tokenExpired, formatter);
			LocalDateTime now = LocalDateTime.now();
			if(now.isAfter(dateTime)) {
				tokenDto = getToken();
				redisComponent.setData(Stock.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
			}
		}
	}

	private ApiTokenDto getToken() {
		RestTemplate restTemplate = new RestTemplate();
				String url = "https://openapi.koreainvestment.com:9443/oauth2/tokenP";
		ApiTokenReqDto apiTokenReqDto = ApiTokenReqDto.builder()
				.appkey(appKey)
				.appsecret(appsecret)
				.build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = null;
		try {
			requestEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(apiTokenReqDto), headers);
		} catch (JsonProcessingException e) {
			System.out.println("CollectService.getToken()");
			e.printStackTrace();
		}
		ResponseEntity<ApiTokenDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiTokenDto.class);

		return response.getBody();
	}

	private void saveCommonInfo(String[] itemCode) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "CTPF1604R");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		for (String code : itemCode) {
			// API 엔드포인트 URL
			String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?PRDT_TYPE_CD=300&PDNO=" + code;

			// HTTP GET 요청 보내기
			ResponseEntity<StockCommonDto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, StockCommonDto.class);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				StockCommonDto responseBody = response.getBody();
				ModelMapper modelMapper = new ModelMapper();
				StockCommon stockCommon = modelMapper.map(responseBody.getOutput(), StockCommon.class);
				stockCommon.setCode(code);
				stockCommonRepository.save(stockCommon);
				log.info("stockCommon = " + stockCommon);
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}

			waiting();
		}
	}

	private void saveCurrentPrice(String[] itemCode){
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "FHKST01010100");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		for (String code : itemCode) {
			// API 엔드포인트 URL
			String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd=" + code;

			// HTTP GET 요청 보내기
			ResponseEntity<StockDetailDto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, StockDetailDto.class);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				StockDetailDto responseBody = response.getBody();
				ModelMapper modelMapper = new ModelMapper();
				StockDetail stockDetail = modelMapper.map(responseBody.getOutput(), StockDetail.class);
				stockDetail.setCode(code);
				stockDetailRepository.save(stockDetail);
				log.info("stockDetail = " + stockDetail);
			} else {
				System.out.println("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}

			waiting();
		}
	}

	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		ApiTokenDto tokenObj = redisComponent.getAndMapToDto(Stock.API_TOKEN.name(), ApiTokenDto.class);
		String token = String.format("%s %s", tokenObj.getTokenType(), tokenObj.getAccessToken());
		headers.set("authorization", token);
		headers.set("appkey", appKey);
		headers.set("appsecret", appsecret);
		return headers;
	}

	private void waiting() {
		// 초당 요청 개수를 제한하기 위한 변수 설정
		int requestsPerSecond = 37;
		long interval = 1000 / requestsPerSecond;

		// 요청 간격 유지
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("InterruptedException occurred: " + e.getMessage());
		}
	}


}
