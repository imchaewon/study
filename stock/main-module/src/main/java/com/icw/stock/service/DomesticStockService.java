package com.icw.stock.service;

import com.icw.stock.model.stock.req.api.common.ApiTokenDTO;
import com.icw.stock.model.stock.req.api.domestic.DomesticCurrentPriceAPIReqDTO;
import com.icw.stock.model.stock.req.api.domestic.StockSimplePriceApiReqDTO;
import com.icw.stock.model.stock.req.domestic.DomesticPriceByPeriodAPIReqDTO;
import com.icw.stock.model.stock.req.domestic.DomesticReqDTO;
import com.icw.stock.model.stock.resp.domestic.CodeNPriceDTO;
import com.icw.stock.model.stock.resp.domestic.DetailInfo;
import com.icw.stock.model.stock.resp.domestic.DomesticCollectPeriodRespDTO;
import com.icw.stock.repository.domestic.StockDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@AllArgsConstructor
public class DomesticStockService implements StockFormatterService {
	private final StockDetailRepository stockDetailRepository;
	private final TokenService tokenService;

	public StockSimplePriceApiReqDTO[] getSimplePrice(String[] arr){
		List<StockSimplePriceApiReqDTO> list = stockDetailRepository.findCustomColumns();

		StockSimplePriceApiReqDTO[] result = new StockSimplePriceApiReqDTO[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			StockSimplePriceApiReqDTO match = list.stream().filter(e -> e.getCode().equals(s)).findAny().get();
			result[i] = match;
		}

		return result;
	}

	public String changeToStr(String reqTxt){
		return changeToStr(reqTxt, null);
	}

	@Override
	public String changeToStr(String reqTxt, String date){
		DomesticReqDTO overseasReqDTO = DomesticReqDTO.of(reqTxt, date);
		return overseasReqDTO.convertToJson();
	}

	@Override
	public DomesticReqDTO changeToDTO(String reqTxt){
		return DomesticReqDTO.of(reqTxt, null);
	}

	@Override
	public DomesticReqDTO changeToDTO(String reqTxt, String date){
		return DomesticReqDTO.of(reqTxt, date);
	}

	public DomesticCollectPeriodRespDTO fetchPriceByPeriod(List<String> reqDTO, String reqDate) {
		tokenService.storeTokenInRedisAndTryCatch();
		return retrieveFromExternalAPI(reqDTO, reqDate);
	}

	public List<DetailInfo> changeToDTOAndFetchCurrentPrice(String reqTxt) {
		DomesticReqDTO overseasReqDTO = changeToDTO(reqTxt);
		return fetchCurrentPrice(overseasReqDTO.getIds());
	}

	public List<DetailInfo> fetchCurrentPrice(List<String> ids) {
		tokenService.storeTokenInRedisAndTryCatch();
		return retrieveFromExternalAPI(ids);
	}

	private DomesticCollectPeriodRespDTO retrieveFromExternalAPI(List<String> ids, String reqDate) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "FHKST03010100");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		AtomicInteger failCnt = new AtomicInteger();
		List<CodeNPriceDTO> codeNPriceDTOS = ids.stream().map(stockCode -> {
			if(failCnt.get() >= 5)
				throw new IllegalArgumentException("휴장일입니다.");
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice")
					.queryParam("FID_COND_MRKT_DIV_CODE", "J")
					.queryParam("FID_INPUT_ISCD", stockCode)
					.queryParam("FID_INPUT_DATE_1", reqDate)
					.queryParam("FID_INPUT_DATE_2", reqDate)
					.queryParam("FID_PERIOD_DIV_CODE", "D")
					.queryParam("FID_ORG_ADJ_PRC", "0");

			// HTTP GET 요청 보내기
			ResponseEntity<DomesticPriceByPeriodAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, DomesticPriceByPeriodAPIReqDTO.class);
			waiting(30); // 각각의 요청을 응답받는데까지 너무 오래걸려서, 병렬로 요청후 다시 순서 맞추는 등의 다른 방법을 써야할듯. (참고로 한번 호출에 100건까지 가능하다고 함)

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				DomesticPriceByPeriodAPIReqDTO responseBody = response.getBody();
				boolean output2IsNull = responseBody == null || responseBody.getOutput2().isEmpty();
				DomesticPriceByPeriodAPIReqDTO.Output2DTO firstValue = output2IsNull ? null : responseBody.getOutput2().get(0);
				boolean stckClprIsNull = output2IsNull || firstValue.getStck_clpr() == null;
				if(stckClprIsNull)
					failCnt.getAndIncrement();
				return new CodeNPriceDTO(stockCode, output2IsNull ? null :
						stckClprIsNull ? null : Integer.parseInt(firstValue.getStck_clpr()));
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			return null;
		}).toList();

		if(ids.size() == failCnt.get())
			throw new IllegalArgumentException("가격정보가 없거나 휴장일입니다.");

		return new DomesticCollectPeriodRespDTO(reqDate, codeNPriceDTOS);
	}

	private List<DetailInfo> retrieveFromExternalAPI(List<String> ids) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "FHKST01010100");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		return ids.stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price")
					.queryParam("FID_COND_MRKT_DIV_CODE", "J")
					.queryParam("FID_INPUT_ISCD", stockCode);

			// HTTP GET 요청 보내기
			ResponseEntity<DomesticCurrentPriceAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, DomesticCurrentPriceAPIReqDTO.class);
			waiting(30);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				DomesticCurrentPriceAPIReqDTO responseBody = response.getBody();
				DetailInfo.DetailInfoBuilder detailInfoDTOBuilder = DetailInfo.builder();
				if (responseBody != null) {
					String stckShrnIscd = responseBody.getOutput().getStck_shrn_iscd();
					String pbr = responseBody.getOutput().getPbr();
					String per = responseBody.getOutput().getPer();
					String base = responseBody.getOutput().getStck_prpr();
					String l52p = responseBody.getOutput().getW52_hgpr();
					String h52p = responseBody.getOutput().getW52_lwpr();
					String l250p = responseBody.getOutput().getD250_lwpr();
					String h250p = responseBody.getOutput().getD250_hgpr();
					if (!"".equals(stckShrnIscd))
						detailInfoDTOBuilder.code(stckShrnIscd);
					if (!"".equals(base))
						detailInfoDTOBuilder.base(Integer.parseInt(base));
					if (!"".equals(l52p))
						detailInfoDTOBuilder.l52wp(Integer.parseInt(l52p));
					if (!"".equals(h52p))
						detailInfoDTOBuilder.h52wp(Integer.parseInt(h52p));
					if (!"".equals(l250p))
						detailInfoDTOBuilder.l250dp(Integer.parseInt(l250p));
					if (!"".equals(h250p))
						detailInfoDTOBuilder.h250dp(Integer.parseInt(h250p));
					if (!"".equals(pbr))
						detailInfoDTOBuilder.pbr(Double.parseDouble(pbr));
					if (!"".equals(per))
						detailInfoDTOBuilder.per(Double.parseDouble(per));
				}
				return detailInfoDTOBuilder.build();
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			return null;
		}).toList();
	}

	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		ApiTokenDTO tokenObj = tokenService.getTokenFromRedis();
		String token = String.format("%s %s", tokenObj.getTokenType(), tokenObj.getAccessToken());
		headers.set("authorization", token);
		headers.set("appkey", tokenService.getAppKey());
		headers.set("appsecret", tokenService.getAppsecret());
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