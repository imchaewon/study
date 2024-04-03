package com.icw.stock.controller;

import com.icw.stock.model.api.StockSimplePrice;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.OverseasCollectDto;
import com.icw.stock.model.stock.req.SimplePriceDto;
import com.icw.stock.model.stock.resp.CodeNPrice;
import com.icw.stock.service.SearchOverseasCollectService;
import com.icw.stock.service.SearchService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController extends CommonResponse {
	private final SearchService searchService;
	private final SearchOverseasCollectService searchOverseasCollectService;

	@PostMapping("simple-price")
	@SwaggerDefault(title = "기본 가격정보", summary = "기본 가격정보를 조회하는 API 입니다.")
	public ResponseEntity<?> simplePrice(@RequestBody SimplePriceDto collectDto) {
		StockSimplePrice[] simplePrice = searchService.getSimplePrice(collectDto.getIds());
		if(simplePrice != null)
			return resSuccess(simplePrice);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("overseas-period-price")
	@SwaggerDefault(title = "해외주식 기간별 가격정보", summary = "해외주식 기간별 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> periodPrice(@RequestBody OverseasCollectDto reqDTO) {
		List<CodeNPrice> result = searchOverseasCollectService.collect(reqDTO);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}
}




