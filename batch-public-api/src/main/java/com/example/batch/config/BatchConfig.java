package com.example.batch.config;

import com.example.batch.dto.PokeApiResponse;
import com.example.batch.entity.Pokemon;
import com.example.batch.job.PokemonProcessor;
import com.example.batch.job.PokemonReader;
import com.example.batch.job.PokemonWriter;
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
}
