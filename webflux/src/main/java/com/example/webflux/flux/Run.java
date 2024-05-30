package com.example.webflux.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Run {
	public static void main(String[] args) {
//		m1();
//		m2();
//		m3();
		m4();
	}

	private static void m1() {
		Flux.just(6, 9, 13)
				.map(n -> n % 2)
				.subscribe(remainder -> log.info("# remainder: {}", remainder));
	}

	private static void m2() {
		Flux.fromArray(new Integer[]{3, 6, 7, 9})
				.filter(n -> n > 6)
				.map(n -> n * 2)
				.subscribe(result -> log.info("# multiply: {}", result));
	}

	private static void m3() {
		// 2개의 Mono를 연결해서 Flux로 변환하는 예제
		Flux<Object> flux = Mono.justOrEmpty(null)
				.concatWith(Mono.justOrEmpty("Jobs"));
		flux.subscribe(data -> log.info("# result: {}", data));
	}

	private static void m4() {
		Flux.concat(
						Flux.just("Venus"),
						Flux.just("Earth"),
						Mono.just("Mars")
				)
				.collectList()
				.subscribe(planetList -> log.info("# Solar System: {}", planetList));
	}
}