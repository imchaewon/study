package com.icw.stock.model.stock.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OverseasCollectPeriodDTO {
	@Schema(example = "20240402")
	String date;
	List<StockCode> stockCodes;

	@Data
	public static class StockCode{
		String excd;
		String symb;
	}
}
