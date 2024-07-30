package com.example.webflux.timeBased;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

public class Ex2 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> Flux.interval(Duration.ofHours(12)).take(2))
                .expectSubscription()
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(24)))
                .expectNextCount(2)
                .verifyComplete();
    }
}