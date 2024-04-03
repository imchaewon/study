package com.icw.common.entity.overseas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OVERSEAS_STOCKS_BY_PERIOD")
public class PriceByPeriod {
	@Id
	private String rsym;
	private String zdiv;
	private String nrec;
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
