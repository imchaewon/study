package com.icw.stock.service.analysis;

import com.icw.common.entity.overseas.OverseasStockSnapshot;
import com.icw.stock.model.analysis.AnalysisResponse;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OverseasAnalysisService {

	private static final int BUSINESS_DAYS_PER_WEEK = 5;
	// SMA200 + 약간의 여유를 확보해야 정상적인 정배열/SMA200 비교가 가능
	private static final int MIN_LOOKBACK_FOR_INDICATORS = 220;
	private static final int RSI_PERIOD = 14;

	private final OverseasStockSnapshotRepository repository;

	public AnalysisResponse analyze(String baseDate, List<Integer> daysWindows, List<Integer> weeksWindows) {
		List<Integer> safeDays = daysWindows == null ? List.of() : daysWindows;
		List<Integer> safeWeeks = weeksWindows == null ? List.of() : weeksWindows;

		int maxDayOffset = safeDays.stream().mapToInt(Integer::intValue).max().orElse(0);
		int maxWeekOffset = safeWeeks.stream().mapToInt(w -> w * BUSINESS_DAYS_PER_WEEK).max().orElse(0);
		int maxOffset = Math.max(maxDayOffset, maxWeekOffset);

		int requiredLookback = Math.max(maxOffset, MIN_LOOKBACK_FOR_INDICATORS);
		LocalDate base = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate from = base.minusDays((long) Math.ceil(requiredLookback * 1.5) + 30);
		String fromStr = from.format(DateTimeFormatter.BASIC_ISO_DATE);

		List<OverseasStockSnapshot> all = repository.findByBaseDateBetween(fromStr, baseDate);

		Map<String, List<OverseasStockSnapshot>> byCode = all.stream()
				.collect(Collectors.groupingBy(OverseasStockSnapshot::getCode));

		List<Map<String, Object>> rows = new ArrayList<>();
		for (Map.Entry<String, List<OverseasStockSnapshot>> entry : byCode.entrySet()) {
			List<OverseasStockSnapshot> seriesAsc = entry.getValue();
			seriesAsc.sort(Comparator.comparing(OverseasStockSnapshot::getBaseDate));

			if (seriesAsc.isEmpty() || !baseDate.equals(seriesAsc.get(seriesAsc.size() - 1).getBaseDate())) {
				continue;
			}

			// baseDate 시점 인덱스(=마지막)
			int t = seriesAsc.size() - 1;

			// RSI, SMA 계산
			Double rsi = computeRsi(seriesAsc, t, RSI_PERIOD);
			Double sma20 = computeSma(seriesAsc, t, 20);
			Double sma50 = computeSma(seriesAsc, t, 50);
			Double sma200 = computeSma(seriesAsc, t, 200);

			OverseasStockSnapshot current = seriesAsc.get(t);
			// desc 정렬한 사본 (윈도우 오프셋 조회용)
			List<OverseasStockSnapshot> seriesDesc = new ArrayList<>(seriesAsc);
			seriesDesc.sort(Comparator.comparing(OverseasStockSnapshot::getBaseDate).reversed());

			boolean arranged = (sma20 != null && sma50 != null && sma200 != null
					&& sma20 > sma50 && sma50 > sma200);
			boolean inverted = (sma20 != null && sma50 != null && sma200 != null
					&& sma20 < sma50 && sma50 < sma200);

			Double bullScore = computeBullScore(current.getNeglectIndex(), rsi, arranged, inverted);
			boolean fSignal = (current.getNeglectIndex() != null && current.getNeglectIndex() >= 75
					&& rsi != null && rsi > 70 && arranged);

			Map<String, Object> row = new LinkedHashMap<>();
			row.put("code", current.getCode());
			row.put("eIcod", current.getEIcod());
			row.put("base", current.getBase());
			row.put("h52p", current.getH52p());
			row.put("l52p", current.getL52p());
			row.put("pvol", current.getPvol());
			row.put("tvol", current.getTvol());
			row.put("tamt", current.getTamt());
			row.put("ordyn", current.getOrdyn());
			row.put("neglectIndex", current.getNeglectIndex());
			row.put("rsi", rsi);
			row.put("sma20", sma20);
			row.put("sma50", sma50);
			row.put("sma200", sma200);
			row.put("arranged", arranged);
			row.put("inverted", inverted);
			row.put("bullScore", bullScore);
			row.put("fSignal", fSignal);

			for (int n : safeDays) {
				addOffsetMetrics(row, seriesDesc, current, n, n + "d");
			}
			for (int w : safeWeeks) {
				addOffsetMetrics(row, seriesDesc, current, w * BUSINESS_DAYS_PER_WEEK, w + "w");
			}

			rows.add(row);
		}

		rows.sort(Comparator.comparing(r -> (String) r.get("code")));

		return new AnalysisResponse(baseDate, safeDays, safeWeeks, rows);
	}

	private Double computeBullScore(Double ni, Double rsi, boolean arranged, boolean inverted) {
		if (ni == null || rsi == null) return null;
		double arrangedScore = arranged ? 100.0 : (inverted ? 0.0 : 50.0);
		return (ni + rsi + arrangedScore) / 3.0;
	}

	/**
	 * Wilder RSI. baseIdx 시점 기준. 데이터 부족 시 null.
	 */
	private Double computeRsi(List<OverseasStockSnapshot> seriesAsc, int baseIdx, int period) {
		if (baseIdx < period) return null;
		double avgGain = 0, avgLoss = 0;
		// 초기 평균 (단순평균)
		for (int i = baseIdx - period + 1; i <= baseIdx - period + period; i++) {
			Double prev = seriesAsc.get(i - 1).getBase();
			Double cur = seriesAsc.get(i).getBase();
			if (prev == null || cur == null) return null;
			double diff = cur - prev;
			avgGain += Math.max(diff, 0);
			avgLoss += Math.max(-diff, 0);
		}
		avgGain /= period;
		avgLoss /= period;
		// Wilder smoothing — 초기 윈도우 이후 시점이 더 있으면 진행
		// 위 루프가 i = baseIdx-period+1 .. baseIdx 까지였으므로 baseIdx 시점 RSI = 위 평균값 기준
		// (즉 마지막 윈도우 = baseDate를 끝점으로 14일치)
		if (avgLoss == 0) return 100.0;
		double rs = avgGain / avgLoss;
		return 100.0 - 100.0 / (1.0 + rs);
	}

	/**
	 * SMA(period) at baseIdx — 직전 period 영업일 평균 (baseIdx 포함).
	 */
	private Double computeSma(List<OverseasStockSnapshot> seriesAsc, int baseIdx, int period) {
		if (baseIdx + 1 < period) return null;
		double sum = 0;
		int count = 0;
		for (int i = baseIdx - period + 1; i <= baseIdx; i++) {
			Double v = seriesAsc.get(i).getBase();
			if (v == null) return null;
			sum += v;
			count++;
		}
		return count > 0 ? sum / count : null;
	}

	private void addOffsetMetrics(
			Map<String, Object> row,
			List<OverseasStockSnapshot> seriesDesc,
			OverseasStockSnapshot current,
			int offset,
			String suffix
	) {
		OverseasStockSnapshot past = (offset >= 0 && offset < seriesDesc.size()) ? seriesDesc.get(offset) : null;
		Double priceChange = computeChangePct(current.getBase(), past != null ? past.getBase() : null);
		Double volumeChange = computeChangePct(toDouble(current.getPvol()), past != null ? toDouble(past.getPvol()) : null);
		Double pastNeglect = past != null ? past.getNeglectIndex() : null;

		row.put("priceChange_" + suffix, priceChange);
		row.put("volumeChange_" + suffix, volumeChange);
		row.put("neglectIndex_" + suffix + "_ago", pastNeglect);
	}

	private Double computeChangePct(Double current, Double past) {
		if (current == null || past == null || past == 0.0) {
			return null;
		}
		return (current - past) / past * 100.0;
	}

	private Double toDouble(Long v) {
		return v == null ? null : v.doubleValue();
	}
}
