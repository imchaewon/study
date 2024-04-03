package com.icw.daemon.model.api;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StockCommonDto {
	private Output output;
	private String rt_cd;
	private String msg_cd;
	private String msg1;

	@ToString
	@Data
	public static class Output {
		private String pdno;
		private String prdt_type_cd;
		private String prdt_name;
		private String prdt_name120;
		private String prdt_abrv_name;
		private String prdt_eng_name;
		private String prdt_eng_name120;
		private String prdt_eng_abrv_name;
		private String std_pdno;
		private String shtn_pdno;
		private String prdt_sale_stat_cd;
		private String prdt_risk_grad_cd;
		private String prdt_clsf_cd;
		private String prdt_clsf_name;
		private String sale_strt_dt;
		private String sale_end_dt;
		private String wrap_asst_type_cd;
		private String ivst_prdt_type_cd;
		private String ivst_prdt_type_cd_name;
		private String frst_erlm_dt;
	}
}
