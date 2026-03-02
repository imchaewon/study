package com.icw.stock.batch;

import com.icw.stock.constant.ProjectConst;
import com.icw.stock.scheduler.OverseasStockScheduler;
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
public class OverseasStockBatchConfig {
	public static final String JOB_NAME = "overseasStockJob";
	private static final String STEP_NAME = "overseasStockStep";

	private final OverseasStockScheduler overseasStockScheduler;

	@Bean(name = JOB_NAME)
	public Job overseasStockJob(JobRepository jobRepository, Step overseasStockStep) {
		return new JobBuilder(JOB_NAME, jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(overseasStockStep)
				.build();
	}

	@Bean
	public Step overseasStockStep(
			JobRepository jobRepository,
			@Qualifier(ProjectConst.JPA.TRANSACTION_MANAGER_REF) PlatformTransactionManager transactionManager
	) {
		return new StepBuilder(STEP_NAME, jobRepository)
				.tasklet((contribution, chunkContext) -> {
					overseasStockScheduler.fetchAndSaveOverseasStockData();
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}

	@Bean(name = "overseasBatchTaskScheduler")
	public TaskScheduler overseasBatchTaskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(1);
		scheduler.setThreadNamePrefix("overseas-batch-scheduler-");
		scheduler.initialize();
		return scheduler;
	}
}
