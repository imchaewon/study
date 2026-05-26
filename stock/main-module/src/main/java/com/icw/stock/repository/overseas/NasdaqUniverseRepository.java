package com.icw.stock.repository.overseas;

import com.icw.common.entity.overseas.NasdaqUniverse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasdaqUniverseRepository extends JpaRepository<NasdaqUniverse, String> {
	List<NasdaqUniverse> findAllByOrderByRankNoAsc();
}
