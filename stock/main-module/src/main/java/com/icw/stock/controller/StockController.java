package com.icw.stock.controller;

import com.icw.stock.model.api.StockSimplePrice;
import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.CollectDto;
import com.icw.stock.service.CollectService;
import com.icw.stock.service.SearchService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController extends CommonResponse {
	private final CollectService collectService;
	private final SearchService searchService;

	@PostMapping("collect")
	public void collect(@RequestBody CollectDto collectDto) {
		collectService.collect(collectDto.getIds());
	}

	@PostMapping("simple-price")
	@SwaggerDefault(summary = "Process BroadcastGroup API", title = "동보 그룹을 생성, 수정, 삭제하는 API 입니다.")
	public ResponseEntity<?> simplePrice(@RequestBody CollectDto collectDto) {
		System.out.println("collectDto = " + collectDto);
		StockSimplePrice[] simplePrice = searchService.getSimplePrice(collectDto.getIds());
		if(simplePrice != null)
			return resSuccess(simplePrice);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}
}




