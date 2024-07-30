package com.example.webflux.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Ex1 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L) // 데이터의 요청개수가 1이기 때문에, 예외가 발생
                .thenConsumeWhile(num -> num >= 1) // emit된 데이터들을 소비
                .verifyComplete();
    }
}