package com.example.webflux.testPublisher;

import com.example.webflux.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Ex1 {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux())) // TestPublisher를 Flux타입으로 변환해서 전달
                .expectSubscription() // 구독이 정상적으로 이루어졌는지 검증
                .then(() -> source.next(2, 4, 6, 8, 10)) // 데이터 emit
                .expectNext(1, 2, 3, 4, 5) // 데이터 검증
                .verifyComplete(); // 테스트 시작 & 정상적으로 완료 검증
    }
}