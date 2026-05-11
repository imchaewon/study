package com.icw.stock.service.analysis;

import com.icw.common.entity.overseas.OverseasStockSnapshot;
import com.icw.stock.model.analysis.MarketRegime;
import com.icw.stock.model.analysis.MarketRegimeResponse;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketRegimeService {

	private static final int SMA_PERIOD = 200;
	private static final int VOL_PERIOD = 20;
	private static final double VOL_THRESHOLD_PCT = 25.0;
	private static final double DAILY_RETURN_CLIP = 0.5; // ±50% 이상치 클립

	private final OverseasStockSnapshotRepository repository;

	public MarketRegimeResponse analyze(String baseDate, int historyDays) {
		// SMA200 + history 만큼의 영업일 데이터가 필요. 달력일은 1.5배 + 여유.
		int neededBusinessDays = SMA_PERIOD + Math.max(historyDays, 0) + 5;
		LocalDate base = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate from = base.minusDays((long) Math.ceil(neededBusinessDays * 1.5) + 30);
		String fromStr = from.format(DateTimeFormatter.BASIC_ISO_DATE);

		List<OverseasStockSnapshot> all = repository.findByBaseDateBetween(fromStr, baseDate);

		// 종목별 시계열로 그룹화
		Map<String, List<OverseasStockSnapshot>> byCode = all.stream()
				.collect(Collectors.groupingBy(OverseasStockSnapshot::getCode));
		for (List<OverseasStockSnapshot> series : byCode.values()) {
			series.sort(Comparator.comparing(OverseasStockSnapshot::getBaseDate));
		}

		// 영업일별 균등가중 일평균 수익률
		TreeMap<String, List<Double>> retsByDate = new TreeMap<>();
		for (List<OverseasStockSnapshot> series : byCode.values()) {
			for (int i = 1; i < series.size(); i++) {
				OverseasStockSnapshot prev = series.get(i - 1);
				OverseasStockSnapshot cur = series.get(i);
				if (prev.getBase() == null || cur.getBase() == null || prev.getBase() <= 0) continue;
				double r = (cur.getBase() - prev.getBase()) / prev.getBase();
				if (r > DAILY_RETURN_CLIP) r = DAILY_RETURN_CLIP;
				if (r < -DAILY_RETURN_CLIP) r = -DAILY_RETURN_CLIP;
				retsByDate.computeIfAbsent(cur.getBaseDate(), k -> new ArrayList<>()).add(r);
			}
		}

		// 인덱스 누적값 (시작 100)
		List<String> dates = new ArrayList<>();
		List<Double> indexVals = new ArrayList<>();
		List<Double> dailyReturns = new ArrayList<>();
		double idx = 100.0;
		boolean started = false;
		for (Map.Entry<String, List<Double>> e : retsByDate.entrySet()) {
			List<Double> rs = e.getValue();
			if (rs.isEmpty()) continue;
			double avg = rs.stream().mapToDouble(Double::doubleValue).average().orElse(0);
			if (!started) {
				// 첫 인덱스 시점 = 가장 오래된 영업일 (수익률 적용 전)
				dates.add(e.getKey());
				indexVals.add(idx);
				dailyReturns.add(0.0);
				started = true;
			}
			idx *= (1 + avg);
			dates.add(e.getKey());
			indexVals.add(idx);
			dailyReturns.add(avg);
		}

		// 각 시점의 SMA200, vol20 계산
		List<MarketRegimeResponse.RegimeSnapshot> snapshots = new ArrayList<>();
		for (int i = 0; i < dates.size(); i++) {
			String d = dates.get(i);
			double iv = indexVals.get(i);
			Double sma = (i >= SMA_PERIOD - 1)
					? avg(indexVals.subList(i - SMA_PERIOD + 1, i + 1))
					: null;
			Double vol = (i >= VOL_PERIOD)
					? stdev(dailyReturns.subList(i - VOL_PERIOD + 1, i + 1)) * Math.sqrt(252) * 100.0
					: null;
			Double dev = (sma != null && sma > 0) ? (iv / sma - 1) * 100 : null;
			MarketRegime regime = classify(iv, sma, vol);
			snapshots.add(new MarketRegimeResponse.RegimeSnapshot(
					d, iv, sma, dev, vol, regime, regime.getLabel()
			));
		}

		// baseDate 이하의 가장 최근 스냅샷 + history
		List<MarketRegimeResponse.RegimeSnapshot> valid = snapshots.stream()
				.filter(s -> s.baseDate().compareTo(baseDate) <= 0)
				.toList();
		if (valid.isEmpty()) {
			return new MarketRegimeResponse(baseDate, null, List.of());
		}
		MarketRegimeResponse.RegimeSnapshot current = valid.get(valid.size() - 1);
		int historyStart = Math.max(0, valid.size() - historyDays);
		List<MarketRegimeResponse.RegimeSnapshot> history = valid.subList(historyStart, valid.size());

		return new MarketRegimeResponse(baseDate, current, history);
	}

	private MarketRegime classify(double indexVal, Double sma, Double vol) {
		if (sma == null || vol == null) return MarketRegime.UNKNOWN;
		boolean aboveTrend = indexVal > sma;
		boolean highVol = vol > VOL_THRESHOLD_PCT;
		if (aboveTrend && !highVol) return MarketRegime.BULL_TREND_LOW_VOL;
		if (aboveTrend) return MarketRegime.BULL_HIGH_VOL;
		if (!highVol) return MarketRegime.BEAR_SIDEWAYS;
		return MarketRegime.BEAR_HIGH_VOL;
	}

	private double avg(List<Double> xs) {
		double s = 0;
		for (double x : xs) s += x;
		return s / xs.size();
	}

	private double stdev(List<Double> xs) {
		if (xs.size() < 2) return 0;
		double m = avg(xs);
		double s = 0;
		for (double x : xs) s += (x - m) * (x - m);
		return Math.sqrt(s / (xs.size() - 1));
	}
}
