package com.icw.stock.model.analysis;

import java.util.List;
import java.util.Map;

public record AnalysisResponse(
		String baseDate,
		List<Integer> daysWindows,
		List<Integer> weeksWindows,
		List<Map<String, Object>> rows
) {
}
