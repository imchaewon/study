package com.icw.stock.controller;

import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.OverseasCurrentPriceDTO;
import com.icw.stock.model.stock.req.OverseasCollectPeriodDTO;
import com.icw.stock.model.stock.resp.CodeN52wPriceDTO;
import com.icw.stock.model.stock.resp.CodeNPriceDTO;
import com.icw.stock.service.OverseasStockService;
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
@RequestMapping("overseas-stock")
@RequiredArgsConstructor
public class OverseasStockController extends CommonResponse {
	private final OverseasStockService overseasStockService;

	@PostMapping("period-price")
	@SwaggerDefault(title = "기간별 시세", summary = "기간별 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> fetchPriceByPeriod(@RequestBody OverseasCollectPeriodDTO reqDTO) {
		List<CodeNPriceDTO> result = overseasStockService.fetchPriceByPeriod(reqDTO);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("current-price")
	@SwaggerDefault(title = "현재가 상세", summary = "현재 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> fetchCurrentPrice(@RequestBody OverseasCurrentPriceDTO reqDTO) {
		List<CodeN52wPriceDTO> result = overseasStockService.fetchCurrentPrice(reqDTO);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}
}