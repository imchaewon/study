package com.icw.stock.controller;

import com.icw.stock.service.alert.MarketAlertService;
import com.icw.stock.service.alert.MarketAlertService.AlertResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/alert")
public class MarketAlertController {

	private final MarketAlertService marketAlertService;

	@PostMapping("/market-check")
	public ResponseEntity<String> trigger() {
		AlertResult r = marketAlertService.checkAndNotify();
		return ResponseEntity.ok("sent=" + r.sent() + " msg=" + r.message());
	}

	/** 임계값/중복 무시 강제 발송 — 테스트용. */
	@PostMapping("/market-check/force")
	public ResponseEntity<String> forceTrigger() {
		AlertResult r = marketAlertService.forceNotify();
		return ResponseEntity.ok("sent=" + r.sent() + " msg=" + r.message());
	}
}
