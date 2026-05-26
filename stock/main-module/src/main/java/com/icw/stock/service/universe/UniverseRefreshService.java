package com.icw.stock.service.universe;

import com.icw.common.entity.overseas.NasdaqUniverse;
import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.repository.overseas.NasdaqUniverseRepository;
import com.icw.stock.service.OverseasStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * stockanalysis.com에서 NASDAQ 시총 상위 500을 스크래핑해 nasdaq_universe 테이블에 반영.
 * 실패 시 기존 universe 유지 (서비스 중단 방지).
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UniverseRefreshService {

	private static final String SOURCE_URL = "https://stockanalysis.com/list/nasdaq-stocks/";
	private static final String USER_AGENT =
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120 Safari/537.36";
	private static final int FETCH_TIMEOUT_MS = 15_000;
	private static final int TARGET_SIZE = 500;
	private static final String DEFAULT_EXCHANGE = "NAS";

	private final NasdaqUniverseRepository repository;
	private final OverseasStockService overseasStockService;

	@Transactional
	public RefreshResult refresh() {
		List<NasdaqUniverse> scraped;
		try {
			scraped = scrape();
		} catch (Exception e) {
			log.error("[UNIVERSE] 스크래핑 실패. 기존 universe 유지", e);
			return new RefreshResult(false, 0, e.getMessage());
		}

		if (scraped.isEmpty()) {
			log.warn("[UNIVERSE] 스크래핑 결과 0건 — 기존 universe 유지");
			return new RefreshResult(false, 0, "scraped 0 rows");
		}

		enrichWithIndustry(scraped);

		// 단순 교체: 기존 전체 삭제 후 신규 insert.
		// 500건 수준이라 부담 없음. truncate+insert 대신 deleteAll로 트랜잭션 보존.
		repository.deleteAllInBatch();
		repository.saveAll(scraped);
		log.info("[UNIVERSE] 갱신 완료 size={}", scraped.size());
		return new RefreshResult(true, scraped.size(), null);
	}

	/**
	 * KIS price-detail API로 업종(e_icod)을 조회해 universe 엔티티에 채운다.
	 * best-effort: 실패한 티커는 industry=null로 두고 성공한 부분만 채운다.
	 */
	private void enrichWithIndustry(List<NasdaqUniverse> scraped) {
		List<ExcdAndSymbDTO> req = new ArrayList<>(scraped.size());
		for (NasdaqUniverse u : scraped) {
			req.add(new ExcdAndSymbDTO(u.getExchange(), u.getTicker()));
		}

		List<DetailInfo> details;
		try {
			details = overseasStockService.fetchCurrentPriceBestEffort(req);
		} catch (Exception e) {
			log.warn("[UNIVERSE] KIS 업종 조회 실패 — industry 없이 universe 저장", e);
			return;
		}

		Map<String, String> industryByTicker = new HashMap<>();
		for (DetailInfo d : details) {
			if (d == null || d.getCode() == null) continue;
			String icod = d.getE_icod();
			if (icod == null || icod.isEmpty()) continue;
			industryByTicker.put(d.getCode(), icod);
		}

		int filled = 0;
		for (NasdaqUniverse u : scraped) {
			String icod = industryByTicker.get(u.getTicker());
			if (icod != null) {
				u.setIndustry(icod);
				filled++;
			}
		}
		log.info("[UNIVERSE] 업종 매핑 완료 — {}개 채워짐, {}개 비어있음", filled, scraped.size() - filled);
	}

	private List<NasdaqUniverse> scrape() throws IOException {
		Document doc = Jsoup.connect(SOURCE_URL)
				.userAgent(USER_AGENT)
				.timeout(FETCH_TIMEOUT_MS)
				.get();

		Elements rows = doc.select("table tbody tr");
		log.info("[UNIVERSE] 파싱된 row 수 = {}", rows.size());

		LocalDateTime now = LocalDateTime.now();
		List<NasdaqUniverse> result = new ArrayList<>(TARGET_SIZE);
		for (Element row : rows) {
			Elements cells = row.select("td");
			if (cells.size() < 4) continue;
			String rankStr = cells.get(0).text().trim();
			String ticker = cells.get(1).text().trim();
			String name = cells.get(2).text().trim();
			String capStr = cells.get(3).text().trim();
			if (ticker.isEmpty()) continue;

			Integer rank = parseIntSafe(rankStr);
			Long marketCap = parseMarketCap(capStr);
			if (rank == null) continue;
			if (rank > TARGET_SIZE) break; // 상위 500까지만

			result.add(NasdaqUniverse.builder()
					.ticker(ticker)
					.exchange(DEFAULT_EXCHANGE)
					.companyName(name.isEmpty() ? null : name)
					.marketCap(marketCap)
					.rankNo(rank)
					.updatedAt(now)
					.build());
		}
		return result;
	}

	/** "5.23T" / "846.93B" / "12.34M" → bytes(USD). 파싱 실패 시 null. */
	private Long parseMarketCap(String s) {
		if (s == null || s.isEmpty() || "-".equals(s)) return null;
		try {
			char unit = s.charAt(s.length() - 1);
			double multiplier = switch (unit) {
				case 'T' -> 1_000_000_000_000d;
				case 'B' -> 1_000_000_000d;
				case 'M' -> 1_000_000d;
				case 'K' -> 1_000d;
				default -> 1d;
			};
			String numStr = Character.isDigit(unit) ? s : s.substring(0, s.length() - 1);
			double n = Double.parseDouble(numStr.replace(",", ""));
			return (long) (n * multiplier);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Integer parseIntSafe(String s) {
		try { return Integer.parseInt(s.trim()); }
		catch (NumberFormatException e) { return null; }
	}

	public record RefreshResult(boolean success, int count, String error) {}
}
