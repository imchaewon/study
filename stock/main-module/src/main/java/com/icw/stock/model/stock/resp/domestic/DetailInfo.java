package com.icw.stock.model.stock.resp.domestic;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailInfo {
	String code;
	Integer base;
	Integer l52p;
	Integer h52p;
	Double pbr;
	Double per;
}