package com.icw.stock.batch;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class OverseasStockBatchScheduler {
	private static final String CRON_EXPRESSION = "0 0 20 * * MON-FRI";
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");
	private static final String BIZ_DATE_PARAM = "bizDate";

	private final JobLauncher jobLauncher;
	private final Job overseasStockJob;
	private final TaskScheduler taskScheduler;

	public OverseasStockBatchScheduler(
			JobLauncher jobLauncher,
			@Qualifier(OverseasStockBatchConfig.JOB_NAME) Job overseasStockJob,
			@Qualifier("overseasBatchTaskScheduler") TaskScheduler taskScheduler
	) {
		this.jobLauncher = jobLauncher;
		this.overseasStockJob = overseasStockJob;
		this.taskScheduler = taskScheduler;
	}

	@PostConstruct
	public void registerScheduler() {
		taskScheduler.schedule(this::runJob, new CronTrigger(CRON_EXPRESSION, ZONE_ID));
		runJob();
	}

	private void runJob() {
		JobParameters jobParameters = createJobParameters();
		String bizDate = jobParameters.getString(BIZ_DATE_PARAM);
		try {
			jobLauncher.run(overseasStockJob, jobParameters);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.info("overseasStockJob는 이미 완료되었습니다. bizDate={}", bizDate);
		} catch (JobExecutionAlreadyRunningException e) {
			log.warn("overseasStockJob가 이미 실행 중입니다. bizDate={}", bizDate);
		} catch (JobRestartException | JobParametersInvalidException e) {
			log.error("overseasStockJob 재시작/파라미터 오류가 발생했습니다. bizDate={}", bizDate, e);
		} catch (Exception e) {
			log.error("overseasStockJob 실행 중 오류가 발생했습니다.", e);
		}
	}

	private JobParameters createJobParameters() {
		String bizDate = LocalDate.now(ZONE_ID).format(DateTimeFormatter.BASIC_ISO_DATE);
		return new JobParametersBuilder()
				.addString(BIZ_DATE_PARAM, bizDate)
				.toJobParameters();
	}
}
