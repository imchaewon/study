package com.example.webflux;

import org.slf4j.Logger;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HelloReactor {
	public static void main(String[] args) {
//		m1();
//		m2();
		m3();
	}

	private static void m1() {
		Mono.just("Hello reactor!")
				.subscribe(e -> System.out.println(e));
	}

	private static void m2() {
		Flux<String> sequence = Flux.just("Hello", "Reactor");
		Flux<String> sequence1 = sequence.map(e -> e.toUpperCase());
		sequence1.subscribe(e -> System.out.println(e));
	}

	private static void m3() {
		Mono.empty()
				.subscribe(
						data -> System.out.println("# emitted data: " + data),
						error -> {},
						() -> System.out.println("# emitted onComplete signal")
				);
	}
}