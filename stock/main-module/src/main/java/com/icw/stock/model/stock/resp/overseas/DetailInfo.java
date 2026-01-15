package com.icw.stock.model.stock.resp.overseas;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailInfo {
	String code;
	Double base;
	Double l52p;
	Double h52p;
	Long pvol;
	Long tvol;
	Long tamt;
	String e_icod;
	String ordyn;
}