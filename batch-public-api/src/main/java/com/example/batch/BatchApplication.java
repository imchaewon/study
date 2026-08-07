package com.example.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
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
    public CommandLineRunner run(JobLauncher jobLauncher, Job pokemonJob) {
        return args -> {
            long startTime = System.currentTimeMillis();
            log.info("배치 시작");

            JobParameters params = new JobParametersBuilder()
                    .addLong("time", startTime)
                    .toJobParameters();
            jobLauncher.run(pokemonJob, params);

            long elapsed = System.currentTimeMillis() - startTime;
            log.info("배치 종료 - 소요 시간: {}ms ({}초)", elapsed, elapsed / 1000.0);
        };
    }
}
