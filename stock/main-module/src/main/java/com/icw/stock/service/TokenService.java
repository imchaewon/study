package com.icw.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.component.RedisComponent;
import com.icw.stock.constant.StockAPIConst;
import com.icw.stock.model.stock.req.api.common.ApiTokenDTO;
import com.icw.stock.model.stock.req.api.common.ApiTokenReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final RedisComponent redisComponent;
	private final ObjectMapper objectMapper;
	@Value("${appkey}")
	private String appKey;
	@Value("${appsecret}")
	private String appsecret;

	public void storeTokenInRedisAndTryCatch() {
		try {
			storeTokenInRedis();
		} catch (JsonProcessingException e) {
			System.err.println("JSON 처리 오류 발생: " + e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.err.println("토큰 저장 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("예상치 못한 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void storeTokenInRedis() throws JsonProcessingException {
		String token = redisComponent.getData(StockAPIConst.API_TOKEN.name());
		ApiTokenDTO tokenDto;
		if (token == null) {
			tokenDto = fetchToken();
			redisComponent.setData(StockAPIConst.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
		} else {
			ApiTokenDTO apiTokenDto = redisComponent.getAndMapToDto(StockAPIConst.API_TOKEN.name(), ApiTokenDTO.class);
			String tokenExpired = apiTokenDto.getAccessTokenExpired();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(tokenExpired, formatter);
			LocalDateTime now = LocalDateTime.now();
			if (now.isAfter(dateTime)) {
				tokenDto = fetchToken();
				redisComponent.setData(StockAPIConst.API_TOKEN.name(), objectMapper.writeValueAsString(tokenDto));
			}
		}
	}

	private ApiTokenDTO fetchToken() {
		RestTemplate restTemplate = new RestTemplate();
		
		// 한국투자증권 API의 올바른 토큰 발급 엔드포인트 시도
		String[] possibleUrls = {
			"https://openapi.koreainvestment.com:9443/oauth2/tokenP",
			"https://openapi.koreainvestment.com:9443/oauth2/token",
			"https://openapi.koreainvestment.com:9443/oauth2/TokenP"
		};
		
		for (String url : possibleUrls) {
			try {
				return attemptTokenRequest(restTemplate, url);
			} catch (Exception e) {
				System.err.println("URL " + url + " 실패: " + e.getMessage());
				continue;
			}
		}
		
		throw new RuntimeException("모든 토큰 발급 엔드포인트가 실패했습니다.");
	}
	
	private ApiTokenDTO attemptTokenRequest(RestTemplate restTemplate, String url) {
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
			System.err.println("JSON 변환 오류: " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			// 먼저 String으로 응답을 받아서 확인
			ResponseEntity<String> stringResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			
			// 응답 상태 코드 확인
			if (stringResponse.getStatusCode().is2xxSuccessful()) {
				// JSON 응답인지 확인
				String responseBody = stringResponse.getBody();
				if (responseBody != null && responseBody.trim().startsWith("{")) {
					// JSON 응답이면 ApiTokenDTO로 변환
					return objectMapper.readValue(responseBody, ApiTokenDTO.class);
				} else {
					// HTML 응답이면 오류 로그 출력
					System.err.println("API가 HTML 응답을 반환했습니다. 응답 내용: " + responseBody);
					throw new RuntimeException("API가 예상하지 못한 형식의 응답을 반환했습니다: " + responseBody);
				}
			} else {
				System.err.println("API 호출 실패. 상태 코드: " + stringResponse.getStatusCode());
				System.err.println("응답 내용: " + stringResponse.getBody());
				throw new RuntimeException("API 호출 실패. 상태 코드: " + stringResponse.getStatusCode());
			}
		} catch (Exception e) {
			System.err.println("토큰 요청 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("토큰 요청 실패", e);
		}
	}

	public ApiTokenDTO getTokenFromRedis() {
		return redisComponent.getAndMapToDto(StockAPIConst.API_TOKEN.name(), ApiTokenDTO.class);
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppsecret() {
		return appsecret;
	}
}