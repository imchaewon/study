package com.icw.stock.controller;

import com.icw.stock.batch.OverseasStockBatchConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/internal/batch")
public class BatchTriggerController {
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

	private final JobLauncher jobLauncher;
	private final Job overseasStockJob;

	public BatchTriggerController(
			JobLauncher jobLauncher,
			@Qualifier(OverseasStockBatchConfig.JOB_NAME) Job overseasStockJob
	) {
		this.jobLauncher = jobLauncher;
		this.overseasStockJob = overseasStockJob;
	}

	@PostMapping("/overseas-stock/run")
	public ResponseEntity<String> runOverseasStockJob(@RequestParam(required = false) String bizDate) {
		String resolvedBizDate = (bizDate == null || bizDate.isBlank())
				? LocalDate.now(ZONE_ID).format(DateTimeFormatter.BASIC_ISO_DATE)
				: bizDate;
		try {
			jobLauncher.run(overseasStockJob, new JobParametersBuilder()
					.addString("bizDate", resolvedBizDate)
					.addLong("requestedAt", System.currentTimeMillis())
					.toJobParameters());
			return ResponseEntity.ok("ok bizDate=" + resolvedBizDate);
		} catch (Exception e) {
			log.error("overseasStockJob 수동 실행 실패 bizDate={}", resolvedBizDate, e);
			return ResponseEntity.internalServerError().body("failed: " + e.getMessage());
		}
	}
}
