package com.icw.stock.scheduler;

import com.icw.stock.service.alert.MarketAlertService;
import com.icw.stock.service.alert.MarketAlertService.AlertResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 매일 08:00 KST에 시장 국면 체크 + 임계값 미달 시 메일 알림.
 * 미국 시장 마감 + 매일 batch 끝난 후 시점.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MarketAlertScheduler {

	private final MarketAlertService marketAlertService;

	@Scheduled(cron = "0 0 8 * * *", zone = "Asia/Seoul")
	public void dailyCheck() {
		AlertResult r = marketAlertService.checkAndNotify();
		log.info("[ALERT] 일일 체크 결과 sent={}, msg={}", r.sent(), r.message());
	}
}
