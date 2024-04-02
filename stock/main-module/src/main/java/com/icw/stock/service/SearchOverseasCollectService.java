package com.icw.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.common.entity.OverseasStocksByPeriod;
import com.icw.stock.component.RedisComponent;
import com.icw.stock.constant.StockCode;
import com.icw.stock.model.api.ApiTokenDto;
import com.icw.stock.model.api.ApiTokenReqDto;
import com.icw.stock.model.api.OverseasDto;
import com.icw.stock.model.stock.req.OverseasCollectDto;
import com.icw.stock.model.stock.resp.CodeNPrice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchOverseasCollectService {
	private final RedisComponent redisComponent;
	private final ObjectMapper objectMapper;
	@Value("${appkey}")
	private String appKey;
	@Value("${appsecret}")
	private String appsecret;

	public List<CodeNPrice> collect(OverseasCollectDto reqDTO){
		try {
			storeTokenInRedis();
		} catch (JsonProcessingException e) {
			System.out.println("CollectService.collect()");
			e.printStackTrace();
		}
		return saveCommonInfo(reqDTO);
//		saveCurrentPrice(itemCode);
	}

	private void storeTokenInRedis() throws JsonProcessingException {
		String token = redisComponent.getData(StockCode.API_TOKEN.name());
		ApiTokenDto tokenDto;
		if(token == null) {
			tokenDto = getToken();
			redisComponent.setData(StockCode.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
		}else{
			ApiTokenDto apiTokenDto = redisComponent.getAndMapToDto(StockCode.API_TOKEN.name(), ApiTokenDto.class);
			String tokenExpired = apiTokenDto.getAccessTokenExpired();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(tokenExpired, formatter);
			LocalDateTime now = LocalDateTime.now();
			if(now.isAfter(dateTime)) {
				tokenDto = getToken();
				redisComponent.setData(StockCode.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
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

	private List<CodeNPrice> saveCommonInfo(OverseasCollectDto reqDTO) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76240000");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		List<CodeNPrice> result = reqDTO.getStockCodes().stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/dailyprice")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb())
					.queryParam("GUBN", "0")
					.queryParam("BYMD", reqDTO.getDate())
					.queryParam("MODP", "1");

			// HTTP GET 요청 보내기
			ResponseEntity<OverseasDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasDto.class);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasDto responseBody = response.getBody();
				OverseasDto.Output2DTO output2DTO = null;
				boolean isNull = responseBody == null || responseBody.getOutput2().isEmpty();
				if(!isNull)
					output2DTO = responseBody.getOutput2().get(0);
				return new CodeNPrice(stockCode.getSymb(), isNull ? null : Double.parseDouble(output2DTO.getClos()));
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			waiting();

			return null;
		}).toList();

		return result;
	}

//	private void saveCurrentPrice(String[] itemCode){
//		// RestTemplate 객체 생성
//		RestTemplate restTemplate = new RestTemplate();
//
//		HttpHeaders headers = createHttpHeaders();
//		headers.set("tr_id", "FHKST01010100");
//
//		// 요청 엔티티 생성
//		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//
//		for (String code : itemCode) {
//			// API 엔드포인트 URL
//			String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd=" + code;
//
//			// HTTP GET 요청 보내기
//			ResponseEntity<StockDetailDto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, StockDetailDto.class);
//
//			// 응답 코드 확인
//			if (response.getStatusCode().is2xxSuccessful()) {
//				// 응답 데이터 출력
//				StockDetailDto responseBody = response.getBody();
//				ModelMapper modelMapper = new ModelMapper();
//				StockDetail stockDetail = modelMapper.map(responseBody.getOutput(), StockDetail.class);
//				stockDetail.setCode(code);
//				stockDetailRepository.save(stockDetail);
//				log.info("stockDetail = " + stockDetail);
//			} else {
//				System.out.println("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
//			}
//
//			waiting();
//		}
//	}

	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		ApiTokenDto tokenObj = redisComponent.getAndMapToDto(StockCode.API_TOKEN.name(), ApiTokenDto.class);
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
