package com.example.webflux.testPublisher;

import com.example.webflux.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Ex3 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.takeNumber(source.flux(), 3))
                .expectSubscription()
                .then(() -> source.emit(1, 2, 3, 4, 5)) // next() + complete()
                .expectNext(1, 2, 3)
                .verifyComplete(); // 테스트 시작 & 정상적으로 예외 검증
    }
}