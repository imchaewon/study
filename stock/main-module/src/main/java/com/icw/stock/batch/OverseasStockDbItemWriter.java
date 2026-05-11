package com.icw.stock.batch;

import com.icw.common.entity.overseas.OverseasStockSnapshot;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class OverseasStockDbItemWriter implements ItemWriter<DetailInfo> {

	private final OverseasStockSnapshotRepository repository;
	private final String baseDate;

	public OverseasStockDbItemWriter(OverseasStockSnapshotRepository repository, String baseDate) {
		this.repository = repository;
		this.baseDate = baseDate;
	}

	@Override
	public void write(Chunk<? extends DetailInfo> chunk) {
		for (DetailInfo info : chunk.getItems()) {
			if (info != null) {
				repository.save(toSnapshot(info));
			}
		}
	}

	private OverseasStockSnapshot toSnapshot(DetailInfo info) {
		return OverseasStockSnapshot.builder()
				.baseDate(baseDate)
				.code(info.getCode() != null ? info.getCode() : "")
				.base(info.getBase())
				.l52p(info.getL52p())
				.h52p(info.getH52p())
				.pvol(info.getPvol())
				.tvol(info.getTvol())
				.tamt(info.getTamt())
				.eIcod(info.getE_icod())
				.ordyn(info.getOrdyn())
				.build();
	}
}
