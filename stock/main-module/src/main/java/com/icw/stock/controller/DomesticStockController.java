package com.icw.stock.controller;

import com.icw.stock.model.stock.req.api.domestic.StockSimplePriceApiReqDTO;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.SimplePriceDTO;
import com.icw.stock.service.DomesticStockService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("domestic-stock")
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
}