package com.icw.stock.controller;

import com.icw.stock.model.api.StockSimplePrice;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.CollectDto;
import com.icw.stock.service.SearchService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController extends CommonResponse {
	private final SearchService searchService;

	@PostMapping("simple-price")
//	@SwaggerDefault(title = "기본 가격정보를 조회하는 API 입니다.", summary = "기본 가격")
	public ResponseEntity<?> simplePrice(@RequestBody CollectDto collectDto) {
		System.out.println("collectDto = " + collectDto);
		StockSimplePrice[] simplePrice = searchService.getSimplePrice(collectDto.getIds());
		if(simplePrice != null)
			return resSuccess(simplePrice);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}
}




