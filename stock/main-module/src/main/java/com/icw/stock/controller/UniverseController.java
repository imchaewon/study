package com.icw.stock.controller;

import com.icw.stock.service.universe.UniverseRefreshService;
import com.icw.stock.service.universe.UniverseRefreshService.RefreshResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/universe")
public class UniverseController {

	private final UniverseRefreshService universeRefreshService;

	@PostMapping("/refresh")
	public ResponseEntity<String> refresh() {
		RefreshResult r = universeRefreshService.refresh();
		if (r.success()) {
			return ResponseEntity.ok("ok count=" + r.count());
		}
		return ResponseEntity.ok("failed (existing universe preserved): " + r.error());
	}
}
