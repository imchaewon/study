package com.icw.stock.scheduler;

import com.icw.stock.service.universe.UniverseRefreshService;
import com.icw.stock.service.universe.UniverseRefreshService.RefreshResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 매주 월요일 새벽 5시(KST) universe 자동 갱신.
 * 한국 시간 새벽이면 미국 시장 마감 후, 그날 시총 데이터가 stockanalysis.com에 반영된 시점.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UniverseRefreshScheduler {

	private final UniverseRefreshService universeRefreshService;

	@Scheduled(cron = "0 0 5 ? * MON", zone = "Asia/Seoul")
	public void weeklyRefresh() {
		log.info("[UNIVERSE] 주간 자동 갱신 시작");
		RefreshResult r = universeRefreshService.refresh();
		log.info("[UNIVERSE] 주간 자동 갱신 결과 success={}, count={}, error={}",
				r.success(), r.count(), r.error());
	}
}
