package com.example.batch.config;

import com.example.batch.dto.PokeApiResponse;
import com.example.batch.dto.TourApiResponse;
import com.example.batch.entity.Pokemon;
import com.example.batch.entity.TourSpot;
import com.example.batch.entity.TourSpotImage;
import com.example.batch.job.*;
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
    private final PokemonReader pokemonReader;
    private final PokemonProcessor pokemonProcessor;
    private final PokemonWriter pokemonWriter;
    private final TourSpotReader tourSpotReader;
    private final TourSpotProcessor tourSpotProcessor;
    private final TourSpotWriter tourSpotWriter;
    private final TourSpotImageReader tourSpotImageReader;
    private final TourSpotImageWriter tourSpotImageWriter;

    @Bean
    public Job pokemonJob() {
        return new JobBuilder("pokemonJob", jobRepository)
                .start(pokemonStep())
                .build();
    }

    @Bean
    public Step pokemonStep() {
        return new StepBuilder("pokemonStep", jobRepository)
                .<PokeApiResponse.PokemonItem, Pokemon>chunk(100, transactionManager)
                .reader(pokemonReader)
                .processor(pokemonProcessor)
                .writer(pokemonWriter)
                .build();
    }

    @Bean
    public Job tourSpotJob() {
        return new JobBuilder("tourSpotJob", jobRepository)
                .start(tourSpotStep())
                .build();
    }

    @Bean
    public Step tourSpotStep() {
        return new StepBuilder("tourSpotStep", jobRepository)
                .<TourApiResponse.Item, TourSpot>chunk(1000, transactionManager)
                .reader(tourSpotReader)
                .processor(tourSpotProcessor)
                .writer(tourSpotWriter)
                .build();
    }

    @Bean
    public Job tourSpotImageJob() {
        return new JobBuilder("tourSpotImageJob", jobRepository)
                .start(tourSpotImageStep())
                .build();
    }

    @Bean
    public Step tourSpotImageStep() {
        return new StepBuilder("tourSpotImageStep", jobRepository)
                .<TourSpotImage, TourSpotImage>chunk(1000, transactionManager)
                .reader(tourSpotImageReader)
                .writer(tourSpotImageWriter)
                .build();
    }
}
