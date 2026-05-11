package com.icw.stock.model.analysis;

import java.util.List;

public record MarketRegimeResponse(
		String baseDate,
		RegimeSnapshot current,
		List<RegimeSnapshot> history
) {

	public record RegimeSnapshot(
			String baseDate,
			Double indexValue,
			Double sma200,
			Double deviationPct,
			Double vol20Pct,
			MarketRegime regime,
			String regimeLabel
	) {
	}
}
