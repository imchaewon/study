package com.icw.stock.scheduler;

import com.icw.common.entity.overseas.NasdaqUniverse;
import com.icw.common.entity.overseas.OverseasStockSnapshot;
import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.repository.overseas.NasdaqUniverseRepository;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import com.icw.stock.service.OverseasStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OverseasStockScheduler {
	private final OverseasStockService overseasStockService;
	private final OverseasStockSnapshotRepository overseasStockSnapshotRepository;
	private final NasdaqUniverseRepository nasdaqUniverseRepository;

	public void fetchAndSaveOverseasStockData() {
		log.info("해외주식 데이터 수집 스케줄러 시작");
		try {
			List<ExcdAndSymbDTO> tickerList = parseAndSortTickers();
			log.info("총 {}개의 티커를 universe에서 로드했습니다.", tickerList.size());

			List<DetailInfo> results = overseasStockService.fetchCurrentPrice(tickerList);
			log.info("API 호출 완료. {}개의 결과를 받았습니다.", results.size());

			saveToDb(results);
			log.info("데이터 DB 저장 완료. base_date: {}", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));

		} catch (Exception e) {
			log.error("해외주식 데이터 수집 중 오류 발생", e);
			throw new IllegalStateException("해외주식 데이터 수집 실패", e);
		}
	}

	public List<ExcdAndSymbDTO> getSortedTickers() {
		return parseAndSortTickers();
	}

	/**
	 * nasdaq_universe 테이블에서 ticker 목록 로드 후 심볼 알파벳순 정렬.
	 * 매주 UniverseRefreshService가 갱신한 시총 상위 500이 대상.
	 */
	public List<ExcdAndSymbDTO> parseAndSortTickers() {
		List<NasdaqUniverse> universe = nasdaqUniverseRepository.findAllByOrderByRankNoAsc();
		return universe.stream()
				.map(u -> new ExcdAndSymbDTO(u.getExchange(), u.getTicker()))
				.sorted(Comparator.comparing(ExcdAndSymbDTO::getSymb))
				.collect(Collectors.toList());
	}

	private void saveToDb(List<DetailInfo> results) {
		String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		overseasStockSnapshotRepository.deleteByBaseDate(baseDate);
		List<OverseasStockSnapshot> snapshots = results.stream()
				.filter(info -> info != null)
				.map(info -> toSnapshot(info, baseDate))
				.collect(Collectors.toList());
		overseasStockSnapshotRepository.saveAll(snapshots);
	}

	private OverseasStockSnapshot toSnapshot(DetailInfo info, String baseDate) {
		return OverseasStockSnapshot.builder()
				.baseDate(baseDate)
				.code(info.getCode() != null ? info.getCode() : "")
				.base(info.getBase())
				.l52p(info.getL52p())
				.h52p(info.getH52p())
				.pvol(info.getPvol())
				.tvol(info.getTvol())
				.tamt(info.getTamt())
				.ordyn(info.getOrdyn())
				.build();
	}
}
