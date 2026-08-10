package com.example.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher,
                                 @Qualifier("pokemonJob") Job pokemonJob,
                                 @Qualifier("tourSpotJob") Job tourSpotJob,
                                 @Qualifier("tourSpotImageJob") Job tourSpotImageJob,
                                 @Qualifier("tourFullJob") Job tourFullJob) {
        return args -> {
            // 실행할 job 선택: "pokemon" / "tourspot" / "tourimage" / "tour" (기본값: pokemon)
            String jobName = args.length > 0 ? args[0] : "pokemon";

            long startTime = System.currentTimeMillis();
            log.info("배치 시작: {}", jobName);

            JobParameters params = new JobParametersBuilder()
                    .addLong("time", startTime)
                    .toJobParameters();

            Job job = switch (jobName) {
                case "tourspot" -> tourSpotJob;
                case "tourimage" -> tourSpotImageJob;
                case "tour" -> tourFullJob;
                default -> pokemonJob;
            };

            jobLauncher.run(job, params);

            long elapsed = System.currentTimeMillis() - startTime;
            log.info("배치 종료 - 소요 시간: {}ms ({}초)", elapsed, elapsed / 1000.0);
        };
    }
}
