package com.icw.stock.repository.common;

import com.icw.common.entity.common.AuditError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditErrorRepository extends JpaRepository<AuditError, Long> {
}
