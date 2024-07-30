package com.example.webflux.hotSequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class Run {
	public static void main(String[] args) throws InterruptedException {
		Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
				.delayElements(Duration.ofSeconds(1)) // 각 요소를 1초 간격으로 발행
				.map(String::toLowerCase)
                .share(); // 콜드 시퀀스를 핫 시퀀스로 변환해주는 연산자

		coldFlux.subscribe(data -> log.info("# Subscriber1: {}", data));

		Thread.sleep(2500);

		coldFlux.subscribe(data -> log.info("# Subscriber2: {}", data));

		Thread.sleep(3000);
	}
}