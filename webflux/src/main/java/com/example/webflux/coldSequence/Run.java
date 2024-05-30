package com.example.webflux.coldSequence;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class Run {
	public static void main(String[] args) {
		Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "PINK"))
				.map(String::toLowerCase);

		coldFlux.subscribe(data -> log.info("# Subscriber1: {}", data));
		log.info("------------------------------");
		coldFlux.subscribe(data -> log.info("# Subscriber2: {}", data));
	}
}