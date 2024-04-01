package com.icw.daemon.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockSimplePrice {
	String code;
	String stck_prpr;
	String w52_hgpr;
	String w52_lwpr;
}
