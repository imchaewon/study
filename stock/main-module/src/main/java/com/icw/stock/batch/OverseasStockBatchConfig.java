package com.icw.stock.batch;

import com.icw.stock.constant.ProjectConst;
import com.icw.stock.model.stock.req.overseas.ExcdAndSymbDTO;
import com.icw.stock.model.stock.resp.overseas.DetailInfo;
import com.icw.stock.scheduler.OverseasStockScheduler;
import com.icw.stock.service.OverseasStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OverseasStockBatchConfig {
	public static final String JOB_NAME = "overseasStockJob";
	private static final String PREPARE_STEP_NAME = "prepareOverseasStockFileStep";
	private static final String STEP_NAME = "overseasStockStep";
	private static final String FILE_HEADER = "Code\tBase\tL52P\tH52P\tPVol\tTAmt\tTVol\tE_Icod\tOrdyn";
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Seoul");

	private final OverseasStockScheduler overseasStockScheduler;
	private final OverseasStockService overseasStockService;

	@Bean(name = JOB_NAME)
	public Job overseasStockJob(
			JobRepository jobRepository,
			@Qualifier(PREPARE_STEP_NAME) Step prepareOverseasStockFileStep,
			@Qualifier(STEP_NAME) Step overseasStockStep
	) {
		return new JobBuilder(JOB_NAME, jobRepository)
				.start(prepareOverseasStockFileStep)
				.next(overseasStockStep)
				.build();
	}

	@Bean(name = PREPARE_STEP_NAME)
	public Step prepareOverseasStockFileStep(
			JobRepository jobRepository,
			@Qualifier(ProjectConst.JPA.TRANSACTION_MANAGER_REF) PlatformTransactionManager transactionManager,
			@Qualifier("prepareOverseasStockFileTasklet") Tasklet prepareOverseasStockFileTasklet
	) {
		return new StepBuilder(PREPARE_STEP_NAME, jobRepository)
				.tasklet(prepareOverseasStockFileTasklet, transactionManager)
				.build();
	}

	@Bean(name = "prepareOverseasStockFileTasklet")
	@StepScope
	public Tasklet prepareOverseasStockFileTasklet(@Value("#{jobParameters['bizDate']}") String bizDate) {
		return (contribution, chunkContext) -> {
			String resolvedBizDate = resolveBizDate(bizDate);
			Path filePath = Path.of(resolveFileName(resolvedBizDate));
			if (Files.notExists(filePath)) {
				Files.writeString(filePath, FILE_HEADER + System.lineSeparator(), StandardCharsets.UTF_8);
			}
			return org.springframework.batch.repeat.RepeatStatus.FINISHED;
		};
	}

	@Bean(name = STEP_NAME)
	public Step overseasStockStep(
			JobRepository jobRepository,
			@Qualifier(ProjectConst.JPA.TRANSACTION_MANAGER_REF) PlatformTransactionManager transactionManager,
			@Qualifier("overseasTickerReader") ItemStreamReader<ExcdAndSymbDTO> overseasTickerReader,
			@Qualifier("overseasTickerProcessor") ItemProcessor<ExcdAndSymbDTO, DetailInfo> overseasTickerProcessor,
			@Qualifier("overseasStockFileWriter") FlatFileItemWriter<DetailInfo> overseasStockFileWriter
	) {
		return new StepBuilder(STEP_NAME, jobRepository)
				.<ExcdAndSymbDTO, DetailInfo>chunk(1, transactionManager)
				.reader(overseasTickerReader)
				.processor(overseasTickerProcessor)
				.writer(overseasStockFileWriter)
				.build();
	}

	@Bean(name = "overseasTickerReader")
	@StepScope
	public ItemStreamReader<ExcdAndSymbDTO> overseasTickerReader() {
		List<ExcdAndSymbDTO> tickers = overseasStockScheduler.getSortedTickers();
		return new TickerListItemReader(tickers);
	}

	@Bean(name = "overseasTickerProcessor")
	public ItemProcessor<ExcdAndSymbDTO, DetailInfo> overseasTickerProcessor() {
		return item -> {
			List<DetailInfo> detailInfos = overseasStockService.fetchCurrentPrice(List.of(item));
			DetailInfo detailInfo = detailInfos.isEmpty() ? null : detailInfos.get(0);
			if (detailInfo == null) {
				throw new IllegalStateException(String.format("해외주식 데이터가 비어있습니다. 종목: %s/%s", item.getExcd(), item.getSymb()));
			}
			return detailInfo;
		};
	}

	@Bean(name = "overseasStockFileWriter")
	@StepScope
	public FlatFileItemWriter<DetailInfo> overseasStockFileWriter(@Value("#{jobParameters['bizDate']}") String bizDate) {
		return new FlatFileItemWriterBuilder<DetailInfo>()
				.name("overseasStockFileWriter")
				.resource(new FileSystemResource(resolveFileName(resolveBizDate(bizDate))))
				.encoding(StandardCharsets.UTF_8.name())
				.lineAggregator(this::formatLine)
				.append(true)
				.shouldDeleteIfExists(false)
				.shouldDeleteIfEmpty(false)
				.saveState(true)
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

	private String resolveBizDate(String bizDate) {
		if (StringUtils.hasText(bizDate)) {
			return bizDate;
		}
		return LocalDate.now(ZONE_ID).format(DateTimeFormatter.BASIC_ISO_DATE);
	}

	private String resolveFileName(String bizDate) {
		return String.format("overseas_stock_%s.txt", bizDate);
	}

	private String formatLine(DetailInfo detailInfo) {
		return String.join("\t",
				safeValue(detailInfo.getCode()),
				safeValue(detailInfo.getBase()),
				safeValue(detailInfo.getL52p()),
				safeValue(detailInfo.getH52p()),
				safeValue(detailInfo.getPvol()),
				safeValue(detailInfo.getTamt()),
				safeValue(detailInfo.getTvol()),
				safeValue(detailInfo.getE_icod()),
				safeValue(detailInfo.getOrdyn())
		);
	}

	private String safeValue(Object value) {
		return value == null ? "" : String.valueOf(value);
	}
}
