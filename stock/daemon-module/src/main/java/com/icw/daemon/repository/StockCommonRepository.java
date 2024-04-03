package com.icw.daemon.repository;

import com.icw.common.entity.StockCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommonRepository extends JpaRepository<StockCommon, Long> {
}
