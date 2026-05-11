package com.icw.stock.model.analysis;

public enum MarketRegime {
	BULL_TREND_LOW_VOL("강세 추세 (저변동)"),
	BULL_HIGH_VOL("강세 (고변동)"),
	BEAR_SIDEWAYS("약세/횡보"),
	BEAR_HIGH_VOL("약세 (고변동)"),
	UNKNOWN("판단불가");

	private final String label;

	MarketRegime(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
