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

import java.util.ArrayList;
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

		// 결과를 저장할 리스트 (원래 순서 유지)
		List<CodeNPriceDTO> codeNPriceDTOS = new ArrayList<>();
		for (int i = 0; i < excdAndSymbs.size(); i++) {
			codeNPriceDTOS.add(null);
		}

		// 실패한 요청의 인덱스를 저장
		List<Integer> failedIndices = new ArrayList<>();

		// 첫 번째 시도: 모든 요청을 빠르게 보내기 (Rate limit 오류가 나더라도 빠르게 시도)
		for (int i = 0; i < excdAndSymbs.size(); i++) {
			ExcdAndSymbDTO stockCode = excdAndSymbs.get(i);
			
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/dailyprice")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb())
					.queryParam("GUBN", "0")
					.queryParam("BYMD", reqDate)
					.queryParam("MODP", "1");

			// 첫 번째 시도는 빠르게 (초당 10개 요청)
			if (i > 0) {
				waiting(10);
			}

			try {
				// HTTP GET 요청 보내기
				ResponseEntity<OverseasPriceByPeriodAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasPriceByPeriodAPIReqDTO.class);
				
				// 응답 처리
				if (response.getStatusCode().is2xxSuccessful()) {
					OverseasPriceByPeriodAPIReqDTO responseBody = response.getBody();
					if (responseBody != null && "0".equals(responseBody.getRt_cd())) {
						OverseasPriceByPeriodAPIReqDTO.Output2DTO output2DTO = null;
						boolean isNull = responseBody.getOutput2() == null || responseBody.getOutput2().isEmpty();
						if (!isNull)
							output2DTO = responseBody.getOutput2().get(0);
						Double price = isNull ? null : Double.parseDouble(output2DTO.getClos());
						Long tvol = isNull ? null : (output2DTO.getTvol() == null || output2DTO.getTvol().isEmpty()) ? null : Long.parseLong(output2DTO.getTvol());
						Double nrec = (responseBody.getOutput1() == null || responseBody.getOutput1().getNrec() == null || responseBody.getOutput1().getNrec().isEmpty()) ? null : Double.parseDouble(responseBody.getOutput1().getNrec());
						codeNPriceDTOS.set(i, new CodeNPriceDTO(stockCode.getSymb(), price, tvol, nrec));
						continue; // 성공한 경우 다음으로
					} else if (responseBody != null && "1".equals(responseBody.getRt_cd())) {
						String msg1 = responseBody.getMsg1();
						if (msg1 != null && msg1.contains("초당 거래건수를 초과")) {
							failedIndices.add(i); // Rate limit 오류는 재시도 대상
							log.warn("[첫 시도 실패] Rate limit 오류 - 종목: {}/{} (인덱스 {}), 메시지: {}", stockCode.getExcd(), stockCode.getSymb(), i, msg1);
							continue;
						} else {
							// 다른 오류인 경우
							failedIndices.add(i);
							log.warn("[첫 시도 실패] API 오류 - 종목: {}/{} (인덱스 {}), rt_cd: {}, msg1: {}", 
									stockCode.getExcd(), stockCode.getSymb(), i, responseBody.getRt_cd(), msg1);
							continue;
						}
					} else if (responseBody != null) {
						// rt_cd가 "0"도 "1"도 아닌 경우
						failedIndices.add(i);
						log.warn("[첫 시도 실패] 예상치 못한 응답 - 종목: {}/{} (인덱스 {}), rt_cd: {}", 
								stockCode.getExcd(), stockCode.getSymb(), i, responseBody.getRt_cd());
						continue;
					}
				} else {
					// HTTP 상태 코드가 2xx가 아닌 경우
					failedIndices.add(i);
					log.warn("[첫 시도 실패] HTTP 오류 - 종목: {}/{} (인덱스 {}), 상태 코드: {}", 
							stockCode.getExcd(), stockCode.getSymb(), i, response.getStatusCode().value());
				}
			} catch (Exception e) {
				failedIndices.add(i);
				log.error("[첫 시도 실패] 예외 발생 - 종목: {}/{} (인덱스 {}), 오류: {}", 
						stockCode.getExcd(), stockCode.getSymb(), i, e.getMessage(), e);
			}
		}

		// 실패한 요청만 재시도 (점진적으로 대기 시간 증가)
		int retryRound = 1;
		int maxRetries = 3;
		while (!failedIndices.isEmpty() && retryRound <= maxRetries) {
			int waitSeconds = retryRound * 2; // 2초, 4초, 6초로 점진적 증가
			log.info("재시도 라운드 {}: {}개의 실패한 요청 재시도 ({}초 대기)", retryRound, failedIndices.size(), waitSeconds);
			waitingSeconds(waitSeconds);

			List<Integer> stillFailed = new ArrayList<>();
			for (Integer i : failedIndices) {
				ExcdAndSymbDTO stockCode = excdAndSymbs.get(i);
				
				// API 엔드포인트 URL
				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/dailyprice")
						.queryParam("AUTH", "")
						.queryParam("EXCD", stockCode.getExcd())
						.queryParam("SYMB", stockCode.getSymb())
						.queryParam("GUBN", "0")
						.queryParam("BYMD", reqDate)
						.queryParam("MODP", "1");

				waiting(5); // 재시도 시에도 요청 간격 유지

				try {
					ResponseEntity<OverseasPriceByPeriodAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasPriceByPeriodAPIReqDTO.class);
					
					if (response.getStatusCode().is2xxSuccessful()) {
						OverseasPriceByPeriodAPIReqDTO responseBody = response.getBody();
						if (responseBody != null && "0".equals(responseBody.getRt_cd())) {
							OverseasPriceByPeriodAPIReqDTO.Output2DTO output2DTO = null;
							boolean isNull = responseBody.getOutput2() == null || responseBody.getOutput2().isEmpty();
							if (!isNull)
								output2DTO = responseBody.getOutput2().get(0);
							Double price = isNull ? null : Double.parseDouble(output2DTO.getClos());
							Long tvol = isNull ? null : (output2DTO.getTvol() == null || output2DTO.getTvol().isEmpty()) ? null : Long.parseLong(output2DTO.getTvol());
							Double nrec = (responseBody.getOutput1() == null || responseBody.getOutput1().getNrec() == null || responseBody.getOutput1().getNrec().isEmpty()) ? null : Double.parseDouble(responseBody.getOutput1().getNrec());
							codeNPriceDTOS.set(i, new CodeNPriceDTO(stockCode.getSymb(), price, tvol, nrec));
							log.debug("[재시도 성공] 종목: {}/{} (인덱스 {})", stockCode.getExcd(), stockCode.getSymb(), i);
							continue; // 성공한 경우 제거
						} else if (responseBody != null) {
							stillFailed.add(i);
							log.warn("[재시도 실패] API 오류 - 종목: {}/{} (인덱스 {}), 라운드: {}, rt_cd: {}, msg1: {}", 
									stockCode.getExcd(), stockCode.getSymb(), i, retryRound, responseBody.getRt_cd(), responseBody.getMsg1());
							continue;
						}
					} else {
						stillFailed.add(i);
						log.warn("[재시도 실패] HTTP 오류 - 종목: {}/{} (인덱스 {}), 라운드: {}, 상태 코드: {}", 
								stockCode.getExcd(), stockCode.getSymb(), i, retryRound, response.getStatusCode().value());
					}
				} catch (Exception e) {
					stillFailed.add(i);
					log.error("[재시도 실패] 예외 발생 - 종목: {}/{} (인덱스 {}), 라운드: {}, 오류: {}", 
							stockCode.getExcd(), stockCode.getSymb(), i, retryRound, e.getMessage(), e);
				}
			}
			failedIndices = stillFailed;
			retryRound++;
		}

		if (!failedIndices.isEmpty()) {
			List<String> failedStocks = new ArrayList<>();
			for (Integer i : failedIndices) {
				ExcdAndSymbDTO stockCode = excdAndSymbs.get(i);
				failedStocks.add(String.format("%s/%s", stockCode.getExcd(), stockCode.getSymb()));
			}
			log.error("[최종 실패] {}개의 요청이 실패했습니다. 실패한 종목: {}", failedIndices.size(), String.join(", ", failedStocks));
		} else {
			log.info("[성공] 모든 {}개의 요청이 성공했습니다.", excdAndSymbs.size());
		}

		return new OverseasCollectPeriodRespDTO(reqDate, codeNPriceDTOS);
	}

	private List<DetailInfo> retrieveFromExternalAPI(List<ExcdAndSymbDTO> reqDTO) {
		// RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = createHttpHeaders();
		headers.set("tr_id", "HHDFS76200200");

		// 요청 엔티티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		// 결과를 저장할 리스트 (원래 순서 유지)
		List<DetailInfo> detailInfos = new ArrayList<>();
		for (int i = 0; i < reqDTO.size(); i++) {
			detailInfos.add(null);
		}

		// 실패한 요청의 인덱스를 저장
		List<Integer> failedIndices = new ArrayList<>();

		// 첫 번째 시도: 모든 요청을 빠르게 보내기 (Rate limit 오류가 나더라도 빠르게 시도)
		for (int i = 0; i < reqDTO.size(); i++) {
			ExcdAndSymbDTO stockCode = reqDTO.get(i);
			
			// API 엔드포인트 URL
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/price-detail")
					.queryParam("AUTH", "")
					.queryParam("EXCD", stockCode.getExcd())
					.queryParam("SYMB", stockCode.getSymb());

			// 첫 번째 시도는 빠르게 (초당 10개 요청)
			if (i > 0) {
				waiting(10);
			}

			try {
				// HTTP GET 요청 보내기
				ResponseEntity<OverseasCurrentPriceAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasCurrentPriceAPIReqDTO.class);
				
				// 응답 처리
				if (response.getStatusCode().is2xxSuccessful()) {
					OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
					if (responseBody != null && "0".equals(responseBody.getRt_cd())) {
						DetailInfo.DetailInfoBuilder detailInfoDTOBuilder = DetailInfo.builder()
								.code(stockCode.getSymb());
						String h52p = responseBody.getOutput().getH52p();
						String l52p = responseBody.getOutput().getL52p();
						String base = responseBody.getOutput().getBase();
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
						detailInfos.set(i, detailInfoDTOBuilder.build());
						continue; // 성공한 경우 다음으로
					} else if (responseBody != null && "1".equals(responseBody.getRt_cd())) {
						String msg1 = responseBody.getMsg1();
						if (msg1 != null && msg1.contains("초당 거래건수를 초과")) {
							failedIndices.add(i); // Rate limit 오류는 재시도 대상
							log.debug("Rate limit 오류 발생 (인덱스 {}): {}", i, msg1);
							continue;
						}
					}
				}
				// 성공하지 못한 경우 실패 목록에 추가
				failedIndices.add(i);
			} catch (Exception e) {
				log.warn("API 요청 중 오류 발생 (인덱스 {}): {}", i, e.getMessage());
				failedIndices.add(i);
			}
		}

		// 실패한 요청만 재시도 (점진적으로 대기 시간 증가)
		int retryRound = 1;
		int maxRetries = 3;
		while (!failedIndices.isEmpty() && retryRound <= maxRetries) {
			int waitSeconds = retryRound * 2; // 2초, 4초, 6초로 점진적 증가
			log.info("재시도 라운드 {}: {}개의 실패한 요청 재시도 ({}초 대기)", retryRound, failedIndices.size(), waitSeconds);
			waitingSeconds(waitSeconds);

			List<Integer> stillFailed = new ArrayList<>();
			for (Integer i : failedIndices) {
				ExcdAndSymbDTO stockCode = reqDTO.get(i);
				
				// API 엔드포인트 URL
				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://openapi.koreainvestment.com:9443/uapi/overseas-price/v1/quotations/price-detail")
						.queryParam("AUTH", "")
						.queryParam("EXCD", stockCode.getExcd())
						.queryParam("SYMB", stockCode.getSymb());

				waiting(5); // 재시도 시에도 요청 간격 유지

				try {
					ResponseEntity<OverseasCurrentPriceAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasCurrentPriceAPIReqDTO.class);
					
					if (response.getStatusCode().is2xxSuccessful()) {
						OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
						if (responseBody != null && "0".equals(responseBody.getRt_cd())) {
							DetailInfo.DetailInfoBuilder detailInfoDTOBuilder = DetailInfo.builder()
									.code(stockCode.getSymb());
							String h52p = responseBody.getOutput().getH52p();
							String l52p = responseBody.getOutput().getL52p();
							String base = responseBody.getOutput().getBase();
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
							detailInfos.set(i, detailInfoDTOBuilder.build());
							log.debug("[재시도 성공] 종목: {}/{} (인덱스 {})", stockCode.getExcd(), stockCode.getSymb(), i);
							continue; // 성공한 경우 제거
						} else if (responseBody != null) {
							stillFailed.add(i);
							log.warn("[재시도 실패] API 오류 - 종목: {}/{} (인덱스 {}), 라운드: {}, rt_cd: {}, msg1: {}", 
									stockCode.getExcd(), stockCode.getSymb(), i, retryRound, responseBody.getRt_cd(), responseBody.getMsg1());
							continue;
						}
					} else {
						stillFailed.add(i);
						log.warn("[재시도 실패] HTTP 오류 - 종목: {}/{} (인덱스 {}), 라운드: {}, 상태 코드: {}", 
								stockCode.getExcd(), stockCode.getSymb(), i, retryRound, response.getStatusCode().value());
					}
				} catch (Exception e) {
					stillFailed.add(i);
					log.error("[재시도 실패] 예외 발생 - 종목: {}/{} (인덱스 {}), 라운드: {}, 오류: {}", 
							stockCode.getExcd(), stockCode.getSymb(), i, retryRound, e.getMessage(), e);
				}
			}
			failedIndices = stillFailed;
			retryRound++;
		}

		if (!failedIndices.isEmpty()) {
			List<String> failedStocks = new ArrayList<>();
			for (Integer i : failedIndices) {
				ExcdAndSymbDTO stockCode = reqDTO.get(i);
				failedStocks.add(String.format("%s/%s", stockCode.getExcd(), stockCode.getSymb()));
			}
			log.error("[최종 실패] {}개의 요청이 실패했습니다. 실패한 종목: {}", failedIndices.size(), String.join(", ", failedStocks));
		} else {
			log.info("[성공] 모든 {}개의 요청이 성공했습니다.", reqDTO.size());
		}

		return detailInfos;
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