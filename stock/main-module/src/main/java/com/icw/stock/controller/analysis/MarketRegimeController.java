package com.icw.stock.controller.analysis;

import com.icw.stock.model.analysis.MarketRegimeResponse;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import com.icw.stock.service.analysis.MarketRegimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis/market-regime")
public class MarketRegimeController {

	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

	private final MarketRegimeService marketRegimeService;
	private final OverseasStockSnapshotRepository repository;

	@GetMapping
	public ResponseEntity<MarketRegimeResponse> analyze(
			@RequestParam(required = false) String baseDate,
			@RequestParam(required = false, defaultValue = "30") int historyDays
	) {
		String resolved = resolveBaseDate(baseDate);
		return ResponseEntity.ok(marketRegimeService.analyze(resolved, historyDays));
	}

	private String resolveBaseDate(String requested) {
		if (requested != null && !requested.isBlank()) return requested;
		String latest = repository.findLatestBaseDate();
		return latest != null
				? latest
				: LocalDate.now(ZONE_ID).format(DateTimeFormatter.BASIC_ISO_DATE);
	}
}
