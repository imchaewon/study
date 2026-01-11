package com.icw.stock.service;

import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.model.stock.req.overseas.OverseasReqDTO;
import com.icw.stock.model.stock.req.api.common.ApiTokenDTO;
import com.icw.stock.model.stock.req.api.overseas.OverseasCurrentPriceAPIReqDTO;
import com.icw.stock.model.stock.req.api.overseas.OverseasPriceByPeriodAPIReqDTO;
import com.icw.stock.model.stock.resp.overseas.CodeNPriceDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.model.stock.resp.overseas.OverseasCollectPeriodRespDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverseasStockService implements StockFormatterService {
	private final TokenService tokenService;

	public OverseasCollectPeriodRespDTO fetchPriceByPeriod(List<ExcdAndSymbDTO> reqDTO, String reqDate) {
		tokenService.storeTokenInRedisAndTryCatch();
		return retrieveFromExternalAPI(reqDTO, reqDate);
	}

	public List<DetailInfo> changeToDTOAndFetchCurrentPrice(String reqTxt) {
		OverseasReqDTO domesticReqDTO = changeToDTO(reqTxt);
		return fetchCurrentPrice(domesticReqDTO.getExcdAndSymbs());
	}

	public List<DetailInfo> fetchCurrentPrice(List<ExcdAndSymbDTO> reqDTO) {
		tokenService.storeTokenInRedisAndTryCatch();
		return retrieveFromExternalAPI(reqDTO);
	}

	@Override
	public String changeToStr(String reqTxt){
		return changeToStr(reqTxt, null);
	}

	@Override
	public String changeToStr(String reqTxt, String date){
		OverseasReqDTO domesticReqDTO = OverseasReqDTO.of(reqTxt, date);
		return domesticReqDTO.convertToJson();
	}

	@Override
	public OverseasReqDTO changeToDTO(String reqTxt){
		return OverseasReqDTO.of(reqTxt, null);
	}

	@Override
	public OverseasReqDTO changeToDTO(String reqTxt, String date){
		return OverseasReqDTO.of(reqTxt, date);
	}

	private OverseasCollectPeriodRespDTO retrieveFromExternalAPI(List<ExcdAndSymbDTO> excdAndSymbs, String reqDate) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76240000");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		List<CodeNPriceDTO> codeNPriceDTOS = excdAndSymbs.stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/dailyprice")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb())
					.queryParam("GUBN", "0")
					.queryParam("BYMD", reqDate)
					.queryParam("MODP", "1");

			// HTTP GET 요청 보내기
			ResponseEntity<OverseasPriceByPeriodAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasPriceByPeriodAPIReqDTO.class);
			waiting(100); // 각각의 요청을 응답받는데까지 너무 오래걸려서, 병렬로 요청후 다시 순서 맞추는 등의 다른 방법을 써야할듯. (참고로 한번 호출에 100건까지 가능하다고 함)

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasPriceByPeriodAPIReqDTO responseBody = response.getBody();
				OverseasPriceByPeriodAPIReqDTO.Output2DTO output2DTO = null;
				boolean isNull = responseBody == null || responseBody.getOutput2().isEmpty();
				if (!isNull)
					output2DTO = responseBody.getOutput2().get(0);
				Double price = isNull ? null : Double.parseDouble(output2DTO.getClos());
				Long tvol = isNull ? null : (output2DTO.getTvol() == null || output2DTO.getTvol().isEmpty()) ? null : Long.parseLong(output2DTO.getTvol());
				Double nrec = (responseBody == null || responseBody.getOutput1() == null || responseBody.getOutput1().getNrec() == null || responseBody.getOutput1().getNrec().isEmpty()) ? null : Double.parseDouble(responseBody.getOutput1().getNrec());
				return new CodeNPriceDTO(stockCode.getSymb(), price, tvol, nrec);
			} else {
				log.error("API 요청에 실패하였습니다. 응답 코드: " + response.getStatusCodeValue());
			}
			return null;
		}).toList();

		return new OverseasCollectPeriodRespDTO(reqDate, codeNPriceDTOS);
	}

	private List<DetailInfo> retrieveFromExternalAPI(List<ExcdAndSymbDTO> reqDTO) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76200200");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		return reqDTO.stream().map(stockCode -> {
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/price-detail")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb());

			// 요청 전 지연 (초당 5개 요청으로 제한)
			waiting(5);

			// 재시도 로직
			int maxRetries = 3;
			int retryCount = 0;
			ResponseEntity<OverseasCurrentPriceAPIReqDTO> response = null;
			
			while (retryCount < maxRetries) {
				try {
					// HTTP GET 요청 보내기
					response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasCurrentPriceAPIReqDTO.class);
					
					// Rate limit 오류 체크
					if (response.getStatusCode().is2xxSuccessful()) {
						OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
						if (responseBody != null && "1".equals(responseBody.getRt_cd())) {
							String msg1 = responseBody.getMsg1();
							if (msg1 != null && msg1.contains("초당 거래건수를 초과")) {
								retryCount++;
								if (retryCount < maxRetries) {
									log.warn("Rate limit 오류 발생. {}초 대기 후 재시도 ({}/{})", retryCount * 2, retryCount, maxRetries);
									waitingSeconds(retryCount * 2); // 재시도할수록 더 길게 대기
									continue;
								}
							}
						}
					}
					break; // 성공하거나 다른 오류인 경우 루프 종료
				} catch (Exception e) {
					retryCount++;
					if (retryCount < maxRetries) {
						log.warn("API 요청 중 오류 발생. {}초 대기 후 재시도 ({}/{}): {}", retryCount * 2, retryCount, maxRetries, e.getMessage());
						waitingSeconds(retryCount * 2);
					} else {
						log.error("API 요청 최종 실패: {}", e.getMessage());
					}
				}
			}

			// 응답 코드 확인
			if (response != null && response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
				DetailInfo.DetailInfoBuilder detailInfoDTOBuilder = DetailInfo.builder()
						.code(stockCode.getSymb());
				if (responseBody != null && "0".equals(responseBody.getRt_cd())) {
					String h52p = responseBody.getOutput().getH52p();
					String l52p = responseBody.getOutput().getL52p();
					String base = responseBody.getOutput().getBase();
					String last = responseBody.getOutput().getLast();
					String pvol = responseBody.getOutput().getPvol();
					String tvol = responseBody.getOutput().getTvol();
					String tamt = responseBody.getOutput().getTamt();
					String e_icod = responseBody.getOutput().getE_icod();
					String ordyn = responseBody.getOutput().getE_ordyn();
					if (!"".equals(h52p))
						detailInfoDTOBuilder.h52p(Double.parseDouble(h52p));
					if (!"".equals(l52p))
						detailInfoDTOBuilder.l52p(Double.parseDouble(l52p));
					if (!"".equals(base))
						detailInfoDTOBuilder.base(Double.parseDouble(base));
					if (!"".equals(tvol))
						detailInfoDTOBuilder.tvol(Long.parseLong(tvol));
					if (!"".equals(pvol))
						detailInfoDTOBuilder.pvol(Long.parseLong(pvol));
					if (!"".equals(tamt))
						detailInfoDTOBuilder.tamt(Long.parseLong(tamt));
					if (!"".equals(e_icod))
						detailInfoDTOBuilder.e_icod(e_icod);
					if (!"".equals(ordyn))
						detailInfoDTOBuilder.ordyn(ordyn);
				} else if (responseBody != null) {
					log.warn("API 응답 오류: rt_cd={}, msg1={}", responseBody.getRt_cd(), responseBody.getMsg1());
				}
				return detailInfoDTOBuilder.build();
			} else {
				if (response != null) {
					log.error("API 요청에 실패하였습니다. 응답 코드: {}", response.getStatusCodeValue());
				}
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

	private void waitingSeconds(int seconds) {
		// 지정된 초만큼 대기
		try {
			Thread.sleep(seconds * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			log.error("InterruptedException occurred: {}", e.getMessage());
		}
	}
}