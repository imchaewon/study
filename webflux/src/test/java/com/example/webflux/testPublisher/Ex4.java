package com.example.webflux.testPublisher;

import com.example.webflux.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Ex4 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(source.flux().log()) // onComplete 신호가 발생하지 않아서 시퀀스 종료가 안됨
                .expectSubscription()
                .then(() -> source.next(1, 2, 3))
                .expectNext(1, 2, 3)
                .verifyComplete(); // 테스트 시작 & 정상적으로 예외 검증
    }
}