package com.icw.stock.model.stock.req.overseas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OverseasCollectPeriodDTO {
	@Schema(example = "20240402")
	String date;
	List<ExcdAndSymbDTO> stockCodes;
}