package com.icw.stock.scheduler;

import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.service.OverseasStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OverseasStockScheduler {
	private final OverseasStockService overseasStockService;

	private static final String TICKER_LIST = """
		NAS	TCOM
		NAS	ASML
		NAS	CHRW
		NAS	DLTR
		NAS	NTRS
		NAS	EXAS
		NAS	ESLT
		NAS	EA
		NAS	BIDU
		NAS	ROST
		NAS	AMAT
		NAS	LRCX
		NAS	NVMI
		NAS	MU
		NAS	WWD
		NAS	JBHT
		NAS	SATS
		NAS	NDSN
		NAS	GMAB
		NAS	HOLX
		NAS	PCAR
		NAS	SNDK
		NAS	MNST
		NAS	RKLB
		NAS	VTRS
		NAS	KTOS
		NAS	CASY
		NAS	TER
		NAS	FER
		NAS	MKSI
		NAS	BBIO
		NAS	LECO
		NAS	ULTA
		NAS	STX
		NAS	BILI
		NAS	GOOG
		NAS	GOOGL
		NAS	KLAC
		NAS	FTAI
		NAS	HTHT
		NAS	WDC
		NAS	NDAQ
		NAS	HST
		NAS	ASTS
		NAS	RGLD
		NAS	AGNC
		NAS	FOX
		NAS	BIIB
		NAS	UAL
		NAS	MAR
		NAS	IBKR
		NAS	FYBR
		NAS	HAS
		NAS	MCHP
		NAS	ADI
		NAS	VOD
		NAS	EXPE
		NAS	WBD
		NAS	LSCC
		NAS	MEDP
		NAS	IONS
		NAS	MTSI
		NAS	FOXA
		NAS	AZN
		NAS	LAMR
		NAS	JKHY
		NAS	MDB
		NAS	PFG
		NAS	EWBC
		NAS	ROIV
		NAS	ILMN
		NAS	INTC
		NAS	ROKU
		NAS	CG
		NAS	INCY
		NAS	FITB
		NAS	HBAN
		NAS	IDXX
		NAS	FUTU
		NAS	CINF
		NAS	STLD
		NAS	RPRX
		NAS	NXPI
		NAS	AMZN
		NAS	SHOP
		NAS	GILD
		NAS	PAA
		NAS	RYAAY
		NAS	UMBF
		NAS	UTHR
		NAS	AVAV
		NAS	ON
		NAS	REGN
		NAS	APP
		NAS	NTRA
		NAS	ENTG
		NAS	CTSH
		NAS	ZM
		NAS	EBAY
		NAS	TSLA
		NAS	BKR
		NAS	LITE
		NAS	NXT
		NAS	TTWO
		NAS	NTES
		NAS	ASND
		NAS	TXRH
		NAS	TRMB
		NAS	PLTR
		NAS	CSX
		NAS	GEHC
		NAS	TROW
		NAS	SSNC
		NAS	FLEX
		NAS	NVDA
		NAS	CSCO
		NAS	AVGO
		NAS	AMGN
		NAS	MPWR
		NAS	ISRG
		NAS	ENSG
		NAS	AEP
		NAS	AAPL
		NAS	ERIC
		NAS	ONC
		NAS	FIGR
		NAS	BKNG
		NAS	EVRG
		NAS	SEIC
		NAS	SOFI
		NAS	PEGA
		NAS	WYNN
		NAS	FCNCA
		NAS	LPLA
		NAS	NBIS
		NAS	TLN
		NAS	HON
		NAS	FSLR
		NAS	RIVN
		NAS	ACGL
		NAS	RMBS
		NAS	CYBR
		NAS	HOOD
		NAS	LNT
		NAS	INSM
		NAS	COKE
		NAS	MDGL
		NAS	CELH
		NAS	CEG
		NAS	AMD
		NAS	CRDO
		NAS	EXEL
		NAS	LAWR
		NAS	ARGX
		NAS	CDNS
		NAS	MOB
		NAS	CME
		NAS	NTAP
		NAS	AFRM
		NAS	AKAM
		NAS	STRL
		NAS	OTEX
		NAS	ARCC
		NAS	TXN
		NAS	REG
		NAS	NBIX
		NAS	MSFT
		NAS	WTW
		NAS	DRS
		NAS	IREN
		NAS	CRWD
		NAS	TECH
		NAS	VRTX
		NAS	PHOE
		NAS	XEL
		NAS	PDD
		NAS	ABNB
		NAS	CCEP
		NAS	IDCC
		NAS	SNPS
		NAS	LIN
		NAS	QCOM
		NAS	ALAB
		NAS	ODFL
		NAS	EXC
		NAS	ICLR
		NAS	LOGI
		NAS	ALNY
		NAS	GFS
		NAS	PANW
		NAS	TTEK
		NAS	FANG
		NAS	COO
		NAS	PEP
		NAS	ORLY
		NAS	WMG
		NAS	RGC
		NAS	BZ
		NAS	TEM
		NAS	TTAN
		NAS	META
		NAS	DOX
		NAS	MRNA
		NAS	GLPI
		NAS	FWONA
		NAS	GRAB
		NAS	ADSK
		NAS	NWS
		NAS	MELI
		NAS	BNTX
		NAS	DASH
		NAS	CMCSA
		NAS	GEN
		NAS	FWONK
		NAS	MRVL
		NAS	FAST
		NAS	PTC
		NAS	ALGN
		NAS	DXCM
		NAS	COST
		NAS	VRSN
		NAS	SAIL
		NAS	AXON
		NAS	NWSA
		NAS	SBUX
		NAS	PODD
		NAS	FFIV
		NAS	CART
		NAS	INTU
		NAS	RGTI
		NAS	DDOG
		NAS	EQIX
		NAS	CRWV
		NAS	OKTA
		NAS	EXE
		NAS	COIN
		NAS	DKNG
		NAS	ZG
		NAS	BMRN
		NAS	ARM
		NAS	ZS
		NAS	Z
		NAS	SWKS
		NAS	KDP
		NAS	CTAS
		NAS	ZBRA
		NAS	TSCO
		NAS	PPC
		NAS	FRHC
		NAS	KSPI
		NAS	SNY
		NAS	DPZ
		NAS	POOL
		NAS	VRSK
		NAS	MANH
		NAS	ADP
		NAS	PSKY
		NAS	CHKP
		NAS	MDLZ
		NAS	NTNX
		NAS	FTNT
		NAS	LINE
		NAS	LULU
		NAS	MMYT
		NAS	BSY
		NAS	AUR
		NAS	JD
		NAS	NFLX
		NAS	MBLY
		NAS	DOCU
		NAS	MORN
		NAS	SMCI
		NAS	ADBE
		NAS	FRMI
		NAS	PAYX
		NAS	CPRT
		NAS	JBDI
		NAS	SBAC
		NAS	CDW
		NAS	WDAY
		NAS	SMMT
		NAS	TW
		NAS	ERIE
		NAS	CSGP
		NAS	CHTR
		NAS	LI
		NAS	SFM
		NAS	KHC
		NAS	CORT
		NAS	KMB
		NAS	TMUS
		NAS	PYPL
		NAS	MSTR
		NAS	TEAM
		NAS	TRI
		NAS	ROP
		NAS	MNDY
		NAS	TTD
		NAS	DUOL
		NAS	AGRZ
		""";

	public void fetchAndSaveOverseasStockData() {
		log.info("해외주식 데이터 수집 스케줄러 시작");
		
		try {
			// 티커 목록 파싱 및 알파벳순 정렬
			List<ExcdAndSymbDTO> tickerList = parseAndSortTickers();
			log.info("총 {}개의 티커를 알파벳순으로 정렬했습니다.", tickerList.size());
			
			// API 호출
			List<DetailInfo> results = overseasStockService.fetchCurrentPrice(tickerList);
			log.info("API 호출 완료. {}개의 결과를 받았습니다.", results.size());
			
			// 결과를 txt 파일로 저장
			String fileName = saveToFile(results);
			log.info("데이터 저장 완료: {}", fileName);
			
		} catch (Exception e) {
			log.error("해외주식 데이터 수집 중 오류 발생", e);
		}
	}

	private List<ExcdAndSymbDTO> parseAndSortTickers() {
		List<ExcdAndSymbDTO> tickers = new ArrayList<>();
		
		String[] lines = TICKER_LIST.split("\n");
		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}
			
			String[] parts = line.split("\t");
			if (parts.length >= 2) {
				String excd = parts[0].trim();
				String symb = parts[1].trim();
				if (!excd.isEmpty() && !symb.isEmpty()) {
					tickers.add(new ExcdAndSymbDTO(excd, symb));
				}
			}
		}
		
		// 티커 심볼 기준으로 알파벳순 정렬
		tickers.sort(Comparator.comparing(ExcdAndSymbDTO::getSymb));
		
		return tickers;
	}

	private String saveToFile(List<DetailInfo> results) throws IOException {
		LocalDate today = LocalDate.now();
		String dateStr = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String fileName = String.format("overseas_stock_%s.txt", dateStr);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			// 헤더 작성
			writer.write("Code\tBase\tL52P\tH52P\tPVol\tTAmt\tTVol\tE_Icod\tOrdyn");
			writer.newLine();
			
			// 데이터 작성
			for (DetailInfo info : results) {
				if (info != null) {
					writer.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
							info.getCode() != null ? info.getCode() : "",
							info.getBase() != null ? info.getBase() : "",
							info.getL52p() != null ? info.getL52p() : "",
							info.getH52p() != null ? info.getH52p() : "",
							info.getPvol() != null ? info.getPvol() : "",
							info.getTamt() != null ? info.getTamt() : "",
							info.getTvol() != null ? info.getTvol() : "",
							info.getE_icod() != null ? info.getE_icod() : "",
							info.getOrdyn() != null ? info.getOrdyn() : ""
					));
					writer.newLine();
				}
			}
		}
		
		return fileName;
	}
}
