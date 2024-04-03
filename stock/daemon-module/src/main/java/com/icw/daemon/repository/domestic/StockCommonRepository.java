package com.icw.daemon.repository.domestic;

import com.icw.common.entity.domestic.StockCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommonRepository extends JpaRepository<StockCommon, Long> {
}
