package com.example.webflux.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Ex6 {
    @Test
    public void sayHelloReactorTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(GeneralExample.takeNumber(source, 500),
                    StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)
//                .expectNext(500)
                .expectNext(499)
                .verifyComplete();
    }
}