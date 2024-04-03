package com.icw.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "stock detail")
@ToString
@Setter
@Getter
public class StockDetail {
	@Id
	private String code;
	@Lob
	private String iscd_stat_cls_code;
	@Lob
	private String marg_rate;
	@Lob
	private String rprs_mrkt_kor_name;
	@Lob
	private String bstp_kor_isnm;
	@Lob
	private String temp_stop_yn;
	@Lob
	private String oprc_rang_cont_yn;
	@Lob
	private String clpr_rang_cont_yn;
	@Lob
	private String crdt_able_yn;
	@Lob
	private String grmn_rate_cls_code;
	@Lob
	private String elw_pblc_yn;
	@Lob
	private String stck_prpr;
	@Lob
	private String prdy_vrss;
	@Lob
	private String prdy_vrss_sign;
	@Lob
	private String prdy_ctrt;
	@Lob
	private String acml_tr_pbmn;
	@Lob
	private String acml_vol;
	@Lob
	private String prdy_vrss_vol_rate;
	@Lob
	private String stck_oprc;
	@Lob
	private String stck_hgpr;
	@Lob
	private String stck_lwpr;
	@Lob
	private String stck_mxpr;
	@Lob
	private String stck_llam;
	@Lob
	private String stck_sdpr;
	@Lob
	private String wghn_avrg_stck_prc;
	@Lob
	private String hts_frgn_ehrt;
	@Lob
	private String frgn_ntby_qty;
	@Lob
	private String pgtr_ntby_qty;
	@Lob
	private String pvt_scnd_dmrs_prc;
	@Lob
	private String pvt_frst_dmrs_prc;
	@Lob
	private String pvt_pont_val;
	@Lob
	private String pvt_frst_dmsp_prc;
	@Lob
	private String pvt_scnd_dmsp_prc;
	@Lob
	private String dmrs_val;
	@Lob
	private String dmsp_val;
	@Lob
	private String cpfn;
	@Lob
	private String rstc_wdth_prc;
	@Lob
	private String stck_fcam;
	@Lob
	private String stck_sspr;
	@Lob
	private String aspr_unit;
	@Lob
	private String hts_deal_qty_unit_val;
	@Lob
	private String lstn_stcn;
	@Lob
	private String hts_avls;
	@Lob
	private String per;
	@Lob
	private String pbr;
	@Lob
	private String stac_month;
	@Lob
	private String vol_tnrt;
	@Lob
	private String eps;
	@Lob
	private String bps;
	@Lob
	private String d250_hgpr;
	@Lob
	private String d250_hgpr_date;
	@Lob
	private String d250_hgpr_vrss_prpr_rate;
	@Lob
	private String d250_lwpr;
	@Lob
	private String d250_lwpr_date;
	@Lob
	private String d250_lwpr_vrss_prpr_rate;
	@Lob
	private String stck_dryy_hgpr;
	@Lob
	private String dryy_hgpr_vrss_prpr_rate;
	@Lob
	private String dryy_hgpr_date;
	@Lob
	private String stck_dryy_lwpr;
	@Lob
	private String dryy_lwpr_vrss_prpr_rate;
	@Lob
	private String dryy_lwpr_date;
	@Lob
	private String w52_hgpr;
	@Lob
	private String w52_hgpr_vrss_prpr_ctrt;
	@Lob
	private String w52_hgpr_date;
	@Lob
	private String w52_lwpr;
	@Lob
	private String w52_lwpr_vrss_prpr_ctrt;
	@Lob
	private String w52_lwpr_date;
	@Lob
	private String whol_loan_rmnd_rate;
	@Lob
	private String ssts_yn;
	@Lob
	private String stck_shrn_iscd;
	@Lob
	private String fcam_cnnm;
	@Lob
	private String cpfn_cnnm;
	@Lob
	private String frgn_hldn_qty;
	@Lob
	private String vi_cls_code;
	@Lob
	private String ovtm_vi_cls_code;
	@Lob
	private String last_ssts_cntg_qty;
	@Lob
	private String invt_caful_yn;
	@Lob
	private String mrkt_warn_cls_code;
	@Lob
	private String short_over_yn;
	@Lob
	private String sltr_yn;
}
