package com.icw.daemon.batch;

import com.icw.daemon.constant.ProjectConst;
import com.icw.daemon.scheduler.DaemonScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class DomesticStockBatchConfig {
	public static final String JOB_NAME = "domesticStockJob";
	private static final String STEP_NAME = "domesticStockStep";

	private final DaemonScheduler daemonScheduler;

	@Bean(name = JOB_NAME)
	public Job domesticStockJob(JobRepository jobRepository, Step domesticStockStep) {
		return new JobBuilder(JOB_NAME, jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(domesticStockStep)
				.build();
	}

	@Bean
	public Step domesticStockStep(
			JobRepository jobRepository,
			@Qualifier(ProjectConst.JPA.TRANSACTION_MANAGER_REF) PlatformTransactionManager transactionManager
	) {
		return new StepBuilder(STEP_NAME, jobRepository)
				.tasklet((contribution, chunkContext) -> {
					daemonScheduler.sendNotification();
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}

	@Bean(name = "domesticBatchTaskScheduler")
	public TaskScheduler domesticBatchTaskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(1);
		scheduler.setThreadNamePrefix("domestic-batch-scheduler-");
		scheduler.initialize();
		return scheduler;
	}
}
