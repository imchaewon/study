package com.example.webflux.backpressure;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class Ex3 {
    @Test
    public void generateNumberTest() {
        StepVerifier
                .create(BackpressureExample.generateNumberByDropStrategy(), 1L) // 데이터의 요청개수가 1이기 때문에, 예외가 발생
                .thenConsumeWhile(num -> num >= 1) // emit된 데이터들을 소비
                .expectComplete() // 시퀀스 정상 완료 검증
                .verifyThenAssertThat() // 시퀀스가 정상적으로 완료된 이후의 추가적인 동작을 검증 가능하도록 함
                .hasDiscardedElements() // 폐기된 데이터가 있는지 검증
                .hasDiscarded(2, 3, 4, 5, 6, 98, 99, 100); // 드롭된 데이터 검증
//                .hasDropped(2, 3, 4, 5, 6, 98, 99, 100); // 여기서의 drop은 백프레셔 전략의 drop이 아니라, hook 메서드 중에서 onNextDropped에 의해서 drop된 데이터가 있는지를 검증하는 operator임
    }
}