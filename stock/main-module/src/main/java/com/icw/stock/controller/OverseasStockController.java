package com.icw.stock.controller;

import com.icw.stock.model.common.response.CommonResponse;
import com.icw.stock.model.stock.req.overseas.OverseasCollectPeriodDTO;
import com.icw.stock.model.stock.req.overseas.OverseasCurrentPriceDTO;
import com.icw.stock.model.stock.req.overseas.OverseasReqDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.model.stock.resp.overseas.OverseasCollectPeriodRespDTO;
import com.icw.stock.service.OverseasStockService;
import com.icw.stock.utils.SwaggerDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("overseas")
@RequiredArgsConstructor
public class OverseasStockController extends CommonResponse {
	private final OverseasStockService overseasStockService;

	@PostMapping("period-price")
	@SwaggerDefault(title = "기간별 시세", summary = "기간별 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> fetchPriceByPeriod(@RequestBody OverseasCollectPeriodDTO reqDTO) {
		OverseasCollectPeriodRespDTO result = overseasStockService.fetchPriceByPeriod(reqDTO.getStockCodes(), reqDTO.getDate());
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("format/period-price")
	@SwaggerDefault(title = "기간별 시세(포맷변환)", summary = "포맷 변환 후 기간별 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> formatAndFetchPriceByPeriod(String date, @RequestBody String reqTxt) {
		OverseasReqDTO domesticReqDTO = overseasStockService.changeToDTO(reqTxt, date);
		OverseasCollectPeriodRespDTO result = overseasStockService.fetchPriceByPeriod(domesticReqDTO.getExcdAndSymbs(), date);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("current-price")
	@SwaggerDefault(title = "현재가 상세", summary = "현재 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> fetchCurrentPrice(@RequestBody OverseasCurrentPriceDTO reqDTO) {
		List<DetailInfo> result = overseasStockService.fetchCurrentPrice(reqDTO.getStockCodes());
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("format/current-price")
	@SwaggerDefault(title = "현재가 상세(포맷변환)", summary = "포맷 변환 후 현재 가격정보를 얻는 API 입니다.")
	public ResponseEntity<?> formatAndFetchCurrentPrice(@RequestBody String reqTxt) {
		List<DetailInfo> result = overseasStockService.changeToDTOAndFetchCurrentPrice(reqTxt);
		if(result != null)
			return resSuccess(result);
		else
			return resFail(HttpStatus.NO_CONTENT, "데이터 없음");
	}

	@PostMapping("formater")
	@SwaggerDefault(title = "포맷 변환기", summary = "포맷을 맞추기 위한 API")
	public String changeFormat(String date, @RequestBody String reqTxt) {
		return overseasStockService.changeToStr(reqTxt, date);
	}
}