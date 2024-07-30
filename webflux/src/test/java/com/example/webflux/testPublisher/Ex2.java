package com.example.webflux.testPublisher;

import com.example.webflux.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Ex2 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.occurError(source.flux()))
                .expectSubscription()
                .then(() -> {
                    source.next(2, 4, 6, 8);
                    source.error(new ArithmeticException()); // 개발자가 직접 예외 발생시킴
                })
                .expectNext(1, 2, 3, 4)
                .verifyError(); // 테스트 시작 & 정상적으로 예외 검증
    }
}