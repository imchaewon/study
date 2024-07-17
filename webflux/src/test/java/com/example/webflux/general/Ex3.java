package com.example.webflux.general;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Ex3 {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier
                .create(GeneralExample.SayHelloReactor())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }
}