package com.icw.stock.controller;

import com.icw.stock.model.stock.req.domestic.DomesticReqDTO;
import com.icw.stock.model.stock.req.api.domestic.StockSimplePriceApiReqDTO;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.SimplePriceDTO;
import com.icw.stock.model.stock.resp.domestic.DetailInfo;
import com.icw.stock.model.stock.resp.domestic.DomesticCollectPeriodRespDTO;
import com.icw.stock.service.DomesticStockService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("domestic")
@RequiredArgsConstructor
public class DomesticStockController extends CommonResponse {
	private final DomesticStockService domesticStockService;

	@PostMapping("simple-price")
	@SwaggerDefault(title = "기본 가격정보", summary = "기본 가격정보를 조회하는 API 입니다.")
	public ResponseEntity<?> simplePrice(@RequestBody SimplePriceDTO collectDto) {
		StockSimplePriceApiReqDTO[] simplePrice = domesticStockService.getSimplePrice(collectDto.getIds());
		if(simplePrice != null)
			return resSuccess(simplePrice);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("format/current-price")
	@SwaggerDefault(title = "현재가 상세(포맷변환)", summary = "포맷 변환 후 현재 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> formatAndFetchCurrentPrice(@RequestBody String reqTxt) {
		DomesticReqDTO domesticReqDTO = domesticStockService.changeToDTO(reqTxt);
		List<DetailInfo>  result = domesticStockService.fetchCurrentPrice(domesticReqDTO.getIds());
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("format/period-price")
	@SwaggerDefault(title = "기간별 시세(포맷변환)", summary = "포맷 변환 후 기간별 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> formatAndFetchPriceByPeriod(@RequestParam String date, @RequestBody String reqTxt) {
		DomesticReqDTO domesticReqDTO = domesticStockService.changeToDTO(reqTxt, date);
		DomesticCollectPeriodRespDTO result = domesticStockService.fetchPriceByPeriod(domesticReqDTO.getIds(), date);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}
}