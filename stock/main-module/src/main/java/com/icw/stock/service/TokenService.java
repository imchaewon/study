package com.icw.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icw.stock.component.RedisComponent;
import com.icw.stock.constant.StockAPIConst;
import com.icw.stock.model.stock.req.api.common.ApiTokenDTO;
import com.icw.stock.model.stock.req.api.common.ApiTokenReqDTO;
import lombok.AllArgsConstructor;
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
			System.out.println("CollectService.collect()");
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