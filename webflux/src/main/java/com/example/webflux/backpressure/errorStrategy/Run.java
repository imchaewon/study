package com.example.webflux.backpressure.errorStrategy;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Run {
	public static void main(String[] args) {
		Flux
				.interval(Duration.ofMillis(1L)) // 매 1밀리초마다 증가하는 숫자를 발행하는 Flux를 생성함 이 Flux는 무한히 증가하는 시퀀스를 생성함
				.onBackpressureError() // 백프레셔가 발생하면 에러를 발생시킴
				.doOnNext(Logger::doOnNext) // 각 데이터가 발생될 때마다 로깅함
				.publishOn(Schedulers.parallel()) // 병렬 스케줄러에서 데이터를 처리하도록 지정함
				.subscribe(data -> { // 데이터 처리
							TimeUtils.sleep(5L); // 발행자에서 emit하는 속도보다 구독자쪽에서 처리하는 속도가 느린 것을 시뮬레이션하기 위함
							Logger.onNext(data);
						},
						error -> { // 에러 처리
							Logger.doOnError(error);
						});

		TimeUtils.sleep(2000L);

	}
}