package com.example.webflux.timeBased;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import java.time.Duration;

public class Ex5 {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedExample.getVoteCount(
                            Flux.interval(Duration.ofMinutes(1))
                        )
                )
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNext(Tuples.of("중구", 15400))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(4)
                .expectComplete()
                .verify();
    }
}