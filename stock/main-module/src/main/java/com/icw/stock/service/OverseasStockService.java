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
				return new CodeNPriceDTO(stockCode.getSymb(), isNull ? null : Double.parseDouble(output2DTO.getClos()));
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

			// HTTP GET 요청 보내기
			ResponseEntity<OverseasCurrentPriceAPIReqDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, OverseasCurrentPriceAPIReqDTO.class);
			waiting(30);

			// 응답 코드 확인
			if (response.getStatusCode().is2xxSuccessful()) {
				// 응답 데이터 출력
				OverseasCurrentPriceAPIReqDTO responseBody = response.getBody();
				DetailInfo.DetailInfoBuilder detailInfoDTOBuilder = DetailInfo.builder()
						.code(stockCode.getSymb());
				if (responseBody != null) {
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