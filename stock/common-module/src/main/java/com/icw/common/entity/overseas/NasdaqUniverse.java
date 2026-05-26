package com.icw.common.entity.overseas;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nasdaq_universe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasdaqUniverse {

	@Id
	@Column(name = "ticker", length = 20, nullable = false)
	private String ticker;

	@Column(name = "exchange", length = 10, nullable = false)
	private String exchange;

	@Column(name = "company_name", length = 255)
	private String companyName;

	@Column(name = "market_cap")
	private Long marketCap;

	@Column(name = "rank_no", nullable = false)
	private Integer rankNo;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
