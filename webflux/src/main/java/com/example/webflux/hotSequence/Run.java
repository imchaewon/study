package com.example.webflux.hotSequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class Run {
	public static void main(String[] args) throws InterruptedException {
		Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
				.delayElements(Duration.ofSeconds(1)) // 원본 Flux를 여러 구독자가 공유함(콜드 시퀀스를 핫 시퀀스로 변환해주는 연산자)
				.map(String::toLowerCase);

		coldFlux.subscribe(data -> log.info("# Subscriber1: {}", data));

		Thread.sleep(2500);

		coldFlux.subscribe(data -> log.info("# Subscriber2: {}", data));

		Thread.sleep(3000);
	}
}