package com.icw.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "stock_common")
@ToString
@Setter
@Getter
public class StockCommon {
	@Id
	private String code;
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
