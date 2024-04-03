package com.icw.stock.model.stock.req;

import lombok.Data;

import java.util.List;

@Data
public class OverseasCurrentPriceDTO {
	List<StockCode> stockCodes;

	@Data
	public static class StockCode{
		String excd;
		String symb;
	}
}
