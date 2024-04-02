package com.icw.stock.model.api;

import lombok.Data;

import java.util.List;

@Data
public class OverseasDto {
	Output1DTO output1;
	List<Output2DTO> output2;

	@Data
	public static class Output1DTO {
		private String rsym;
		private String zdiv;
		private String nrec;
	}

	@Data
	public static class Output2DTO {
		private String xymd;
		private String clos;
		private String sign;
		private String diff;
		private String rate;
		private String open;
		private String high;
		private String low;
		private String tvol;
		private String tamt;
		private String pbid;
		private String vbid;
		private String pask;
		private String vask;
	}
}