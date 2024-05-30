package com.icw.stock.model.stock.resp.domestic;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailInfo {
	String code;
	Integer base;
	Integer l250dp;
	Integer h250dp;
	Integer l52wp;
	Integer h52wp;
	Double pbr;
	Double per;
}