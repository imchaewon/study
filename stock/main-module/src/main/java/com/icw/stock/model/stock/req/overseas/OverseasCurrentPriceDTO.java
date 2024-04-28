package com.icw.stock.model.stock.req.overseas;

import lombok.Data;

import java.util.List;

@Data
public class OverseasCurrentPriceDTO {
	List<ExcdAndSymbDTO> stockCodes;
}