package com.example.java_.z.주식;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class 현재가격 {
	public static void main(String[] args) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		String itemCode = null;
		itemCode = "000660";

		// API 엔드포인트 URL
		String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd=" + itemCode;

		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6IjE3YzAwNjQ2LTUxM2YtNDk4Ni05NGMwLWUxNWQwY2Q2MWMzNCIsImlzcyI6InVub2d3IiwiZXhwIjoxNzA5NTMxODc1LCJpYXQiOjE3MDk0NDU0NzUsImp0aSI6IlBTZ2VtODJWb1E2REJoUXZ5UW13V0RXb0t6S1ozRU1iN05HdCJ9.6tt3gefgKv35xTptzIxp8K8WKGJ500xkGN96a6KTNlU2GTzEHJS6cNkfsLKlOjzL8kmiOKCl28XHa87OQazkHA");
		headers.set("appkey", "PSgem82VoQ6DBhQvyQmwWDWoKzKZ3EMb7NGt");
		headers.set("appsecret", "arhGW/AN7OadewwB8FWvBV5B3bn+of1Ty0FQSmso955SN8jhtFpRoB0id1ssmTSAmpf4a549JCh80Eub/AfJ3Zf/0SPPvJpPSwo1NoxvD4O4CCkvWbpnGqX9l9rBbWd82tppKS7/x4BGmWmA/LJEL9x2MwsMgtnUuXUBgfBc97YEPfSpYVI=");
		headers.set("tr_id", "FHKST01010100");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		// HTTP GET 요청 보내기
		ResponseEntity<StockItemDto> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, StockItemDto.class);

		// 응답 코드 확인
		if (response.getStatusCode().is2xxSuccessful()) {
			// 응답 데이터 출력
			StockItemDto responseBody = response.getBody();

//			System.out.println(responseBody);
			Output output = responseBody.getOutput();
			output.getStck_prpr();
		} else {
			System.out.println("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
		}

	}
}

















