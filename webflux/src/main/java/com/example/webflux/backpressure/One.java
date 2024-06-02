package com.example.webflux.backpressure;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class One {
	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(Logger::doOnNext) // 발행자는 요청한 개수 만큼 데이터를 emit
				.doOnRequest(Logger::doOnRequest) // 구독자가 요청한 데이터의 개수 출력
				.subscribe(new BaseSubscriber<>() {
					@Override
					protected void hookOnSubscribe(Subscription subscription) { // 구독이 시작될 때 호출
						request(1); // 첫 번째 데이터 요청
					}

					@Override
					protected void hookOnNext(Integer value) { // 각 데이터의 항목이 발행될 때 호출
						TimeUtils.sleep(2000L);
						Logger.onNext(value); // 구독자쪽에서 전달받은 데이터를 출력
						request(1); // 1개의 데이터 요청
					}
				});
	}
}