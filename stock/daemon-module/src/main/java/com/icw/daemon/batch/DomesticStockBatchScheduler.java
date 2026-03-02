package com.icw.daemon.batch;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Slf4j
@Component
public class DomesticStockBatchScheduler {
	private static final String CRON_EXPRESSION = "0 0 8 * * MON-FRI";
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

	private final JobLauncher jobLauncher;
	private final Job domesticStockJob;
	private final TaskScheduler taskScheduler;

	public DomesticStockBatchScheduler(
			JobLauncher jobLauncher,
			@Qualifier(DomesticStockBatchConfig.JOB_NAME) Job domesticStockJob,
			@Qualifier("domesticBatchTaskScheduler") TaskScheduler taskScheduler
	) {
		this.jobLauncher = jobLauncher;
		this.domesticStockJob = domesticStockJob;
		this.taskScheduler = taskScheduler;
	}

	@PostConstruct
	public void registerScheduler() {
		taskScheduler.schedule(this::runJob, new CronTrigger(CRON_EXPRESSION, ZONE_ID));
	}

	private void runJob() {
		try {
			jobLauncher.run(domesticStockJob, createJobParameters());
		} catch (Exception e) {
			log.error("domesticStockJob 실행 중 오류가 발생했습니다.", e);
		}
	}

	private JobParameters createJobParameters() {
		return new JobParametersBuilder()
				.addLong("requestedAt", System.currentTimeMillis())
				.toJobParameters();
	}
}
