package com.icw.stock.model.stock.req.api.overseas;

import lombok.Data;

@Data
public class OverseasCurrentPriceAPIReqDTO {
	private Output output;
	private String rt_cd;
	private String msg_cd;
	private String msg1;

	@Data
	public static class Output {
		private String rsym;
		private String zdiv;
		private String curr;
		private String vnit;
		private String open;
		private String high;
		private String low;
		private String last;
		private String base;
		private String pvol;
		private String pamt;
		private String uplp;
		private String dnlp;
		private String h52p;
		private String h52d;
		private String l52p;
		private String l52d;
		private String perx;
		private String pbrx;
		private String epsx;
		private String bpsx;
		private String shar;
		private String mcap;
		private String tomv;
		private String t_xprc;
		private String t_xdif;
		private String t_xrat;
		private String p_xprc;
		private String p_xdif;
		private String p_xrat;
		private String t_rate;
		private String p_rate;
		private String t_xsgn;
		private String p_xsng;
		private String e_ordyn;
		private String e_hogau;
		private String e_icod;
		private String e_parp;
		private String tvol;
		private String tamt;
		private String etyp_nm;
	}
}