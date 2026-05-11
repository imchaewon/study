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

	// 미국 시장 1주 = 5영업일 가정
	private static final int BUSINESS_DAYS_PER_WEEK = 5;

	private final OverseasStockSnapshotRepository repository;

	public AnalysisResponse analyze(String baseDate, List<Integer> daysWindows, List<Integer> weeksWindows) {
		List<Integer> safeDays = daysWindows == null ? List.of() : daysWindows;
		List<Integer> safeWeeks = weeksWindows == null ? List.of() : weeksWindows;

		int maxDayOffset = safeDays.stream().mapToInt(Integer::intValue).max().orElse(0);
		int maxWeekOffset = safeWeeks.stream().mapToInt(w -> w * BUSINESS_DAYS_PER_WEEK).max().orElse(0);
		int maxOffset = Math.max(maxDayOffset, maxWeekOffset);

		// 영업일 maxOffset → 주말/공휴일 포함 달력일은 약 1.5배 + 30일 여유
		LocalDate base = LocalDate.parse(baseDate, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate from = base.minusDays((long) Math.ceil(maxOffset * 1.5) + 30);
		String fromStr = from.format(DateTimeFormatter.BASIC_ISO_DATE);

		List<OverseasStockSnapshot> all = repository.findByBaseDateBetween(fromStr, baseDate);

		Map<String, List<OverseasStockSnapshot>> byCode = all.stream()
				.collect(Collectors.groupingBy(OverseasStockSnapshot::getCode));

		List<Map<String, Object>> rows = new ArrayList<>();
		for (Map.Entry<String, List<OverseasStockSnapshot>> entry : byCode.entrySet()) {
			List<OverseasStockSnapshot> series = entry.getValue();
			series.sort(Comparator.comparing(OverseasStockSnapshot::getBaseDate).reversed());

			if (series.isEmpty() || !baseDate.equals(series.get(0).getBaseDate())) {
				continue; // baseDate 데이터가 없는 종목은 스킵
			}

			OverseasStockSnapshot current = series.get(0);
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

			for (int n : safeDays) {
				addOffsetMetrics(row, series, current, n, n + "d");
			}
			for (int w : safeWeeks) {
				addOffsetMetrics(row, series, current, w * BUSINESS_DAYS_PER_WEEK, w + "w");
			}

			rows.add(row);
		}

		rows.sort(Comparator.comparing(r -> (String) r.get("code")));

		return new AnalysisResponse(baseDate, safeDays, safeWeeks, rows);
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
