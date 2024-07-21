package com.example.webflux.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Ex2 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByErrorStrategy(), 1L) // 데이터의 요청개수가 1이기 때문에, 예외가 발생
                .thenConsumeWhile(num -> num >= 1) // emit된 데이터들을 소비
                .expectError() // 예외가 발생함을 검증
                .verifyThenAssertThat() // 예외가 발생한 이후의 추가적인 동작을 검증 가능하도록 함
                .hasDiscardedElements() // 폐기된 데이터가 있는지 검증
                .hasDiscarded(2) // 폐기된 데이터 검증
                .hasDroppedElements() // 드롭된 데이터가 있는지 검증
                .hasDropped(3, 4, 5, 6, 98, 99, 100); // 드롭된 데이터 검증
    }
}