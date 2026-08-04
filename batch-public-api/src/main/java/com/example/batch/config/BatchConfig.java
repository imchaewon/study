package com.example.batch.config;

import com.example.batch.dto.TourApiResponse;
import com.example.batch.entity.TourSpot;
import com.example.batch.job.TourSpotProcessor;
import com.example.batch.job.TourSpotReader;
import com.example.batch.job.TourSpotWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final TourSpotReader tourSpotReader;
    private final TourSpotProcessor tourSpotProcessor;
    private final TourSpotWriter tourSpotWriter;

    @Bean
    public Job tourSpotJob() {
        return new JobBuilder("tourSpotJob", jobRepository)
            .start(tourSpotStep())
            .build();
    }

    @Bean
    public Step tourSpotStep() {
        return new StepBuilder("tourSpotStep", jobRepository)
            .<TourApiResponse.Item, TourSpot>chunk(100, transactionManager)
            .reader(tourSpotReader)
            .processor(tourSpotProcessor)
            .writer(tourSpotWriter)
            .build();
    }
}
