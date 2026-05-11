package com.icw.stock.controller.analysis;

import com.icw.stock.model.analysis.AnalysisResponse;
import com.icw.stock.service.analysis.OverseasAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis/overseas")
public class OverseasAnalysisController {

	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

	private final OverseasAnalysisService overseasAnalysisService;

	@GetMapping
	public ResponseEntity<AnalysisResponse> analyze(
			@RequestParam(required = false) String baseDate,
			@RequestParam(required = false) List<Integer> daysWindows,
			@RequestParam(required = false) List<Integer> weeksWindows
	) {
		String resolvedBaseDate = (baseDate == null || baseDate.isBlank())
				? LocalDate.now(ZONE_ID).format(DateTimeFormatter.BASIC_ISO_DATE)
				: baseDate;
		return ResponseEntity.ok(overseasAnalysisService.analyze(resolvedBaseDate, daysWindows, weeksWindows));
	}
}
