package com.example.webflux.general;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Ex5 {
    @Test
    public void sayHelloReactorTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier
                .create(GeneralExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }
}