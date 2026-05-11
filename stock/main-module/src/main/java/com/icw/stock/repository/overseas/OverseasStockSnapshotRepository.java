package com.icw.stock.repository.overseas;

import com.icw.common.entity.overseas.OverseasStockSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OverseasStockSnapshotRepository extends JpaRepository<OverseasStockSnapshot, Long> {

	@Modifying
	@Query("DELETE FROM OverseasStockSnapshot o WHERE o.baseDate = :baseDate")
	void deleteByBaseDate(@Param("baseDate") String baseDate);
}
