package com.icw.stock.model.stock.resp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeN52wPriceDTO {
	String code;
	Double base;
	Double h52p;
	Double l52p;
}
