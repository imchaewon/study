package com.example.webflux.mono;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Run {
	public static void main(String[] args) {
//		m1();
//		m2();
		m3();
	}

	private static void m1() {
		Mono.just("Hello Reactor!")
				.subscribe(data -> log.info("# emitted data: {}", data));
	}

	private static void m2() {
		Mono.empty()
				.subscribe(
						data -> log.info("# emitted data: {}", data),
						error -> {
						},
						() -> log.info("# emitted onComplete singnal")
				);
	}

	private static void m3() {
		WebClient webClient = WebClient.create("http://worldtimeapi.org");

		CountDownLatch latch = new CountDownLatch(1);

		webClient.get()
				.uri("/api/timezone/Asia/Seoul")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.map(json -> json.get("datetime").asText())
				.subscribe(
						data -> System.out.println("# emitted data: " + data),
						error -> System.out.println(error.getMessage()),
						() -> {
							System.out.println("# emitted onComplete signal");
							latch.countDown();
						}
				);

		try {
			latch.await(); // 카운트가 0이 될 때까지 대기
			System.out.println("끝~");
		} catch (Exception e) {
			System.out.println("TestWebflux.m3()");
			e.printStackTrace();
		}
	}


}