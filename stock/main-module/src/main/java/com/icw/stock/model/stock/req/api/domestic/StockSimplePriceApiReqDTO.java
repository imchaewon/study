package com.icw.stock.model.stock.req.api.domestic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockSimplePriceApiReqDTO {
	String code;
	String stck_prpr;
	String w52_lwpr;
	String w52_hgpr;
}