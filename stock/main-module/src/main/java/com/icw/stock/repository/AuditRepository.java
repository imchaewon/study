package com.icw.stock.repository;

import com.icw.common.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
	Optional<Audit> findByUuid(String uuid);
}
