package com.icw.stock.service.alert;

import com.icw.stock.model.analysis.MarketRegimeResponse;
import com.icw.stock.repository.overseas.OverseasStockSnapshotRepository;
import com.icw.stock.service.analysis.MarketRegimeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 시장 국면이 임계값을 넘으면 메일 알림.
 * - 매일 한 번 (백엔드 batch 끝난 후) 체크
 * - peakDrawdownPct <= threshold면 메일 발송
 * - 같은 날 중복 발송 방지 (메모리 ref). 재시작하면 reset (큰 문제 X — 매일 한 번씩 보내는 거라)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MarketAlertService {

	private final JavaMailSender mailSender;
	private final MarketRegimeService marketRegimeService;
	private final OverseasStockSnapshotRepository snapshotRepository;

	@Value("${spring.mail.password:}")
	private String mailPassword;

	@Value("${stock.alert.peak-drawdown-threshold-pct:-12.0}")
	private double thresholdPct;

	@Value("${stock.alert.recipient:}")
	private String recipient;

	private LocalDate lastSentDate = null;

	@PostConstruct
	void logConfig() {
		boolean mailReady = mailPassword != null && !mailPassword.isBlank();
		boolean recipientReady = recipient != null && !recipient.isBlank();
		log.info("[ALERT] mailPassword={} recipient={} threshold={}%",
				mailReady ? "set" : "MISSING",
				recipientReady ? recipient : "MISSING",
				thresholdPct);
	}

	/**
	 * 시장 국면 체크 + 임계값 미만이면 메일 발송.
	 * 외부에서 호출 가능 (스케줄러/수동 트리거).
	 * @return 발송 여부 + 메시지
	 */
	public AlertResult checkAndNotify() {
		return doCheck(false);
	}

	/** 임계값/중복 무시하고 강제 발송 — 발송 자체 테스트용. */
	public AlertResult forceNotify() {
		return doCheck(true);
	}

	private AlertResult doCheck(boolean force) {
		String latestBaseDate = snapshotRepository.findLatestBaseDate();
		if (latestBaseDate == null) {
			return new AlertResult(false, "no data");
		}
		MarketRegimeResponse resp = marketRegimeService.analyze(latestBaseDate, 0);
		MarketRegimeResponse.RegimeSnapshot snap = resp.current();
		if (snap == null || snap.peakDrawdownPct() == null) {
			return new AlertResult(false, "no drawdown data");
		}
		double dd = snap.peakDrawdownPct();
		if (!force && dd > thresholdPct) {
			return new AlertResult(false,
					String.format("drawdown %.2f%% above threshold %.1f%%", dd, thresholdPct));
		}

		LocalDate today = LocalDate.now();
		if (!force && today.equals(lastSentDate)) {
			return new AlertResult(false, "already sent today");
		}

		try {
			sendMail(snap);
			lastSentDate = today;
			return new AlertResult(true, String.format("sent: drawdown %.2f%%", dd));
		} catch (Exception e) {
			log.error("[ALERT] 메일 발송 실패", e);
			return new AlertResult(false, "send failed: " + e.getMessage());
		}
	}

	private void sendMail(MarketRegimeResponse.RegimeSnapshot snap) {
		if (mailPassword == null || mailPassword.isBlank()) {
			throw new IllegalStateException("MAIL_PASSWORD 환경변수가 설정되지 않음");
		}
		if (recipient == null || recipient.isBlank()) {
			throw new IllegalStateException("stock.alert.recipient가 설정되지 않음");
		}

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(recipient);
		msg.setSubject(String.format("⚠ 시장 알림: 30일 high 대비 %.2f%% (%s)",
				snap.peakDrawdownPct(), snap.baseDate()));
		msg.setText(String.format(
				"[Stock Analysis 시장 국면 알림]%n%n"
						+ "기준일: %s%n"
						+ "인덱스: %.2f%n"
						+ "SMA200: %.2f (이격도 %+.2f%%)%n"
						+ "20일 변동성: %.2f%%%n"
						+ "30일 high 대비: %+.2f%%%n"
						+ "국면: %s%n%n"
						+ "권장 조치:%n"
						+ "- 신규 진입 중단%n"
						+ "- 12주 보유 포지션 손절 검토 (-12%% 임계 도달)%n",
				snap.baseDate(),
				snap.indexValue(),
				snap.sma200() == null ? 0 : snap.sma200(),
				snap.deviationPct() == null ? 0 : snap.deviationPct(),
				snap.vol20Pct() == null ? 0 : snap.vol20Pct(),
				snap.peakDrawdownPct(),
				snap.regimeLabel()
		));
		mailSender.send(msg);
		log.info("[ALERT] 메일 발송 완료 to={}, drawdown={}%", recipient, snap.peakDrawdownPct());
	}

	public record AlertResult(boolean sent, String message) {}
}
