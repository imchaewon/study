package com.icw.stock.model.stock.resp.overseas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OverseasCollectPeriodRespDTO {
	String date;
	List<CodeNPriceDTO> codeNPriceDTOs;
}