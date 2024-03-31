package com.icw.stock.repository;

import com.icw.stock.entity.StockCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommonRepository extends JpaRepository<StockCommon, Long> {
}
