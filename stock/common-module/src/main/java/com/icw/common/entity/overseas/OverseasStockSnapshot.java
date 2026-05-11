package com.icw.common.entity.overseas;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "overseas_stock_snapshot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverseasStockSnapshot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "base_date", nullable = false, length = 8)
	private String baseDate;

	@Column(name = "code", nullable = false, length = 50)
	private String code;

	@Column(name = "base")
	private Double base;

	@Column(name = "l52p")
	private Double l52p;

	@Column(name = "h52p")
	private Double h52p;

	@Column(name = "pvol")
	private Long pvol;

	@Column(name = "tvol")
	private Long tvol;

	@Column(name = "tamt")
	private Long tamt;

	@Column(name = "e_icod", length = 200)
	private String eIcod;

	@Column(name = "ordyn", length = 50)
	private String ordyn;
}
