package com.example.webflux.timeBased;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Ex4 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> Flux.interval(Duration.ofHours(12)).take(2))
                .expectSubscription()
                .expectNextCount(2)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }
}