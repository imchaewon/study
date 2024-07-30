package com.example.webflux.flux;

import com.example.webflux.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Run {
	public static void main(String[] args) {
//		m1();
//		m2();
//		m3();
//		m4();
        m5();
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
						Flux.just("Venus", "asdf"),
						Flux.just("Earth"),
						Mono.just("Mars")
				)
				.collectList()
				.subscribe(planetList -> log.info("# Solar System: {}", planetList));
	}

    private static void m5() {
        Flux.interval(Duration.ofMillis(2))
                .take(10)
                .subscribe(System.out::println);

        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (Exception e) {
        	System.out.println("Run.m5()");
        	e.printStackTrace();
        }
    }
}