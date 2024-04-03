package com.icw.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.component.RedisComponent;
import com.icw.stock.constant.StockCode;
import com.icw.stock.model.stock.req.api.common.ApiTokenDTO;
import com.icw.stock.model.stock.req.api.common.ApiTokenReqDTO;
import com.icw.stock.model.stock.req.api.overseas.OverseasCurrentPriceAPIReqDTO;
import com.icw.stock.model.stock.req.api.overseas.OverseasPriceByPeriodAPIReqDTO;
import com.icw.stock.model.stock.req.OverseasCurrentPriceDTO;
import com.icw.stock.model.stock.req.OverseasCollectPeriodDTO;
import com.icw.stock.model.stock.resp.CodeN52wPriceDTO;
import com.icw.stock.model.stock.resp.CodeNPriceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverseasStockService {
	private final RedisComponent redisComponent;
	private final ObjectMapper objectMapper;
	@Value("${appkey}")
	private String appKey;
	@Value("${appsecret}")
	private String appsecret;

	public List<CodeNPriceDTO> fetchPriceByPeriod(OverseasCollectPeriodDTO reqDTO){
		storeTokenInRedisAndHandlerError();
		return retrieveFromExternalAPI(reqDTO);
	}

	public List<CodeN52wPriceDTO> fetchCurrentPrice(OverseasCurrentPriceDTO reqDTO){
		storeTokenInRedisAndHandlerError();
		return retrieveFromExternalAPI(reqDTO);
	}

	private void storeTokenInRedisAndHandlerError(){
		try {
			storeTokenInRedis();
		} catch (JsonProcessingException e) {
			System.out.println("CollectService.collect()");
			e.printStackTrace();
		}
	}

	private void storeTokenInRedis() throws JsonProcessingException {
		String token = redisComponent.getData(StockCode.API_TOKEN.name());
		ApiTokenDTO tokenDto;
		if(token == null) {
			tokenDto = getToken();
			redisComponent.setData(StockCode.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
		}else{
			ApiTokenDTO apiTokenDto = redisComponent.getAndMapToDto(StockCode.API_TOKEN.name(), ApiTokenDTO.class);
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

	private ApiTokenDTO getToken() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://openapi.koreainvestment.com:9443/oauth2/tokenP";
		ApiTokenReqDTO apiTokenReqDto = ApiTokenReqDTO.builder()
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
		ResponseEntity<ApiTokenDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ApiTokenDTO.class);

		return response.getBody();
	}

	private List<CodeNPriceDTO> retrieveFromExternalAPI(OverseasCollectPeriodDTO reqDTO) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76240000");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		return reqDTO.getStockCodes().stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/dailyprice")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb())
					.queryParam("GUBN", "0")
					.queryParam("BYMD", reqDTO.getDate())
					.queryParam("MODP", "1");

			// HTTP GET 요청 보내기
			ResponseEntity<OverseasPriceByPeriodAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasPriceByPeriodAPIReqDTO.class);
			waiting(100); // 각각의 요청을 응답받는데까지 너무 오래걸려서, 병렬로 요청후 다시 순서 맞추는 등의 다른 방법을 써야할듯

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasPriceByPeriodAPIReqDTO responseBody = response.getBody();
				OverseasPriceByPeriodAPIReqDTO.Output2DTO output2DTO = null;
				boolean isNull = responseBody == null || responseBody.getOutput2().isEmpty();
				if(!isNull)
					output2DTO = responseBody.getOutput2().get(0);
				return new CodeNPriceDTO(stockCode.getSymb(), isNull ? null : Double.parseDouble(output2DTO.getClos()));
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			return null;
		}).toList();
	}

	private List<CodeN52wPriceDTO> retrieveFromExternalAPI(OverseasCurrentPriceDTO reqDTO) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76200200");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		return reqDTO.getStockCodes().stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/price-detail")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb());

			// HTTP GET 요청 보내기
			ResponseEntity<OverseasCurrentPriceAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasCurrentPriceAPIReqDTO.class);
			waiting(30);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
				CodeN52wPriceDTO.CodeN52wPriceDTOBuilder codeN52wPriceBuilder = CodeN52wPriceDTO.builder()
						.code(stockCode.getSymb());
				if(responseBody != null){
					String h52p = responseBody.getOutput().getH52p();
					String l52p = responseBody.getOutput().getL52p();
					String base = responseBody.getOutput().getBase();
					if(!"".equals(h52p))
						codeN52wPriceBuilder.h52p(Double.parseDouble(h52p));
					if(!"".equals(l52p))
						codeN52wPriceBuilder.l52p(Double.parseDouble(l52p));
					if(!"".equals(base))
						codeN52wPriceBuilder.base(Double.parseDouble(base));
				}
				return codeN52wPriceBuilder.build();
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			return null;
		}).toList();
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
		ApiTokenDTO tokenObj = redisComponent.getAndMapToDto(StockCode.API_TOKEN.name(), ApiTokenDTO.class);
		String token = String.format("%s %s", tokenObj.getTokenType(), tokenObj.getAccessToken());
		headers.set("authorization", token);
		headers.set("appkey", appKey);
		headers.set("appsecret", appsecret);
		return headers;
	}

	private void waiting(double perSec) {
		// 초당 요청 개수를 제한하기 위한 변수 설정
		long interval = (long) (1000 / perSec);

		// 요청 간격 유지
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.err.println("InterruptedException occurred: " + e.getMessage());
		}
	}

}
