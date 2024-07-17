package com.example.webflux.general;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Ex2 {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.SayHelloReactor())
                .expectSubscription()
                .expectNext("Hello")
                .expectNext("Reactor")
                .expectComplete()
                .verify();
    }
}