package com.icw.stock.repository;

import com.icw.stock.entity.StockDetail;
import com.icw.stock.model.api.StockSimplePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDetailRepository extends JpaRepository<StockDetail, Long> {
	@Query("SELECT new com.icw.stock.model.api.StockSimplePrice(e.code, e.stck_prpr, e.w52_hgpr, e.w52_lwpr) FROM StockDetail e")
	List<StockSimplePrice> findCustomColumns(); // 원하는 컬럼을 담은 DTO를 반환하는 쿼리
}
