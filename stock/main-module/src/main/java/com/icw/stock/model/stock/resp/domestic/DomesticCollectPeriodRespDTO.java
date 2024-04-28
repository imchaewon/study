package com.icw.stock.model.stock.resp.domestic;

import com.icw.stock.model.stock.resp.domestic.CodeNPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DomesticCollectPeriodRespDTO {
	String date;
	List<CodeNPriceDTO> codeNPriceDTOs;
}