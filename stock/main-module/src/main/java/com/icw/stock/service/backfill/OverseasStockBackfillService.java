package com.icw.stock.service.backfill;

import com.icw.common.entity.overseas.OverseasStockSnapshot;
import com.icw.stock.model.stock.req.api.overseas.OverseasPriceByPeriodAPIReqDTO;
import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import com.icw.stock.scheduler.OverseasStockScheduler;
import com.icw.stock.service.OverseasStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverseasStockBackfillService {

	private static final long PER_REQUEST_DELAY_MS = 120; // 초당 ~8건 (KIS rate limit 여유)
	private static final int MAX_API_RETRIES = 3;

	private final OverseasStockService overseasStockService;
	private final OverseasStockScheduler overseasStockScheduler;
	private final OverseasStockSnapshotRepository repository;

	@Async
	public void backfillAsync(String startDate, String endDate) {
		long t0 = System.currentTimeMillis();
		List<ExcdAndSymbDTO> tickers = overseasStockScheduler.parseAndSortTickers();
		log.info("[BACKFILL] 시작 {} ~ {}, 종목 {}개", startDate, endDate, tickers.size());

		int totalInserted = 0;
		int totalSkipped = 0;
		List<String> failed = new ArrayList<>();

		for (int i = 0; i < tickers.size(); i++) {
			ExcdAndSymbDTO ticker = tickers.get(i);
			try {
				int[] r = backfillTicker(ticker, startDate, endDate);
				totalInserted += r[0];
				totalSkipped += r[1];
				if (i % 20 == 0) {
					log.info("[BACKFILL] 진행 {}/{} (누적 +{} row, skip {})",
							i + 1, tickers.size(), totalInserted, totalSkipped);
				}
			} catch (Exception e) {
				log.error("[BACKFILL] 종목 실패 {}/{}: {}",
						ticker.getExcd(), ticker.getSymb(), e.getMessage());
				failed.add(ticker.getExcd() + "/" + ticker.getSymb());
			}
		}

		long sec = (System.currentTimeMillis() - t0) / 1000;
		log.info("[BACKFILL] 완료 elapsed={}s, inserted={}, skipped={}, failedTickers={}",
				sec, totalInserted, totalSkipped, failed.size());
		if (!failed.isEmpty()) {
			log.warn("[BACKFILL] 실패 종목 목록: {}", String.join(", ", failed));
		}
	}

	private int[] backfillTicker(ExcdAndSymbDTO ticker, String startDate, String endDate) {
		Set<String> existing = new HashSet<>(repository.findBaseDatesByCode(ticker.getSymb()));

		// API가 반환하는 30일치 시계열을 모아 startDate ~ endDate 범위 row 전체 수집
		List<OverseasPriceByPeriodAPIReqDTO.Output2DTO> collected = new ArrayList<>();
		String cursor = endDate;
		String prevOldest = null; // 무한루프 방지

		while (cursor.compareTo(startDate) >= 0) {
			List<OverseasPriceByPeriodAPIReqDTO.Output2DTO> chunk = callApiWithRetry(ticker, cursor);
			if (chunk.isEmpty()) break;

			// 최신순(desc) 정렬 가정이지만 안전하게 정렬
			chunk.sort(Comparator.comparing(OverseasPriceByPeriodAPIReqDTO.Output2DTO::getXymd).reversed());
			collected.addAll(chunk);

			String oldest = chunk.get(chunk.size() - 1).getXymd();
			if (oldest.equals(prevOldest)) break; // 같은 결과 반복 → 종료
			prevOldest = oldest;
			if (oldest.compareTo(startDate) <= 0) break;

			// 다음 호출은 oldest 직전 날짜를 기준일로 (영업일 정확히 모르므로 -1일)
			cursor = decrementDate(oldest);
			sleep(PER_REQUEST_DELAY_MS);
		}

		// 시계열 정렬 (오래된 → 최신) — pvol 채우기 위해
		collected.sort(Comparator.comparing(OverseasPriceByPeriodAPIReqDTO.Output2DTO::getXymd));

		List<OverseasStockSnapshot> toSave = new ArrayList<>();
		Long prevTvol = null;
		int skipped = 0;
		for (OverseasPriceByPeriodAPIReqDTO.Output2DTO d : collected) {
			String xymd = d.getXymd();
			if (xymd == null || xymd.isEmpty()) continue;
			if (xymd.compareTo(startDate) < 0 || xymd.compareTo(endDate) > 0) continue;
			if (existing.contains(xymd)) {
				prevTvol = parseLong(d.getTvol()); // pvol 매핑 일관성 위해 계속 추적
				skipped++;
				continue;
			}
			Long tvol = parseLong(d.getTvol());
			OverseasStockSnapshot snap = OverseasStockSnapshot.builder()
					.baseDate(xymd)
					.code(ticker.getSymb())
					.base(parseDouble(d.getClos()))
					.tvol(tvol)
					.pvol(prevTvol) // 직전 영업일 거래량
					.tamt(parseLong(d.getTamt()))
					.build();
			toSave.add(snap);
			prevTvol = tvol;
		}

		saveAll(toSave);
		return new int[]{toSave.size(), skipped};
	}

	@Transactional
	public void saveAll(List<OverseasStockSnapshot> snaps) {
		if (snaps.isEmpty()) return;
		repository.saveAll(snaps);
	}

	private List<OverseasPriceByPeriodAPIReqDTO.Output2DTO> callApiWithRetry(ExcdAndSymbDTO ticker, String cursor) {
		IllegalStateException last = null;
		for (int attempt = 1; attempt <= MAX_API_RETRIES; attempt++) {
			try {
				return overseasStockService.fetchDailyPriceRange(ticker, cursor);
			} catch (IllegalStateException e) {
				last = e;
				log.debug("[BACKFILL] API 재시도 {}/{} {}/{}: {}",
						attempt, MAX_API_RETRIES, ticker.getExcd(), ticker.getSymb(), e.getMessage());
				sleep(500L * attempt);
			}
		}
		throw last == null ? new IllegalStateException("unknown") : last;
	}

	private String decrementDate(String yyyymmdd) {
		java.time.LocalDate d = java.time.LocalDate.parse(yyyymmdd,
				java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
		return d.minusDays(1).format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
	}

	private Double parseDouble(String s) {
		if (s == null || s.isEmpty()) return null;
		try { return Double.parseDouble(s); } catch (NumberFormatException e) { return null; }
	}

	private Long parseLong(String s) {
		if (s == null || s.isEmpty()) return null;
		try { return Long.parseLong(s); } catch (NumberFormatException e) { return null; }
	}

	private void sleep(long ms) {
		try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	}
}
