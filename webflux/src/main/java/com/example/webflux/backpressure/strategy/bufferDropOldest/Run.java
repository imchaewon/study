package com.example.webflux.backpressure.strategy.bufferDropOldest;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Run {
	public static void main(String[] args) {
		Flux
				.interval(Duration.ofMillis(300L)) // 0.3초마다 데이터를 emit함
				.doOnNext(data -> Logger.info("# emitted by original Flux: {}", data)) // 스트림의 각 항목이 방출될 때마다 로깅함
				.onBackpressureBuffer(2, // 버퍼의 최대 크기를 2로 지정. 버퍼에 2개의 데이터가 들어가면 가득 참
						dropped -> Logger.info("# Overflow & dropped: {}", dropped), // drop이 발생하면 로그로 출력
						BufferOverflowStrategy.DROP_OLDEST) // 버퍼가 가득 찼을 때, 버퍼안에 들어있는 데이터중에서 가장 오래된 데이터를 Drop하는 전략
				.doOnNext(data -> Logger.info("# emitted by Buffer: {}", data)) // 버퍼에서 emit되는 데이터를 처리
				.publishOn(Schedulers.parallel(), false, 3) // 병렬 스케줄러에서 데이터를 처리하도록 지정함. 3번째 파라미터인 prefetch는 추가되는 스레드 앞에서 일종의 버퍼역할을 함
				.subscribe(data -> { // 데이터 처리
							TimeUtils.sleep(1000L); // 발행자에서 emit하는 속도보다 구독자쪽에서 처리하는 속도가 느린 것을 시뮬레이션하기 위함
							Logger.onNext(data); // 구독자가 데이터를 실제로 처리할 때 로깅
						},
						error -> { // 에러 처리
							Logger.doOnError(error);
						});

		TimeUtils.sleep(3000L);
	}
}