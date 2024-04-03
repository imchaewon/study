package com.icw.daemon.model.api.domestic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockSimplePriceDTO {
	String code;
	String stck_prpr;
	String w52_hgpr;
	String w52_lwpr;
}
