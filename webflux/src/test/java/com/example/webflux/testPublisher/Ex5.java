package com.example.webflux.testPublisher;

import com.example.webflux.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Ex5 {
    @Test
    public void divideByTwoTest() {
//        TestPublisher<Integer> source = TestPublisher.create(); // 이렇게 하면, 데이터가 emit되기 전에 이미 리액티브스트림즈의 사양을 준수하지 않았기 때문에 이때 이미 에러가 발생함
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL); // null도 emit되게 허용. 일단 데이터를 emit하고 이후에 가공처리하는 과정에서 에러가 발생됨

        StepVerifier
                .create(GeneralExample.divideByTwo(source.flux())) // onComplete 신호가 발생하지 않아서 시퀀스 종료가 안됨
                .expectSubscription()
                .then(() -> source.next(2, 4, 6, 8, null))
                .expectNext(1, 2, 3, 4)
                .verifyComplete(); // 테스트 시작 & 정상적으로 예외 검증
    }
}