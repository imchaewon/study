package com.example.webflux.backpressure;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Two {
	public static int cnt = 0;
	public static void main(String[] args) {
		Flux.range(1, 5)
				.doOnNext(Logger::doOnNext) // 발행자는 요청한 개수 만큼 데이터를 emit
				.doOnRequest(Logger::doOnRequest) // 구독자가 요청한 데이터의 개수 출력
				.subscribe(new BaseSubscriber<>() {
					@Override
					protected void hookOnSubscribe(Subscription subscription) { // 구독이 시작될 때 호출
						request(2); // 첫 번째 데이터 요청
					}

					@Override
					protected void hookOnNext(Integer value) { // 각 데이터의 항목이 발행될 때 호출
						cnt++;
						Logger.onNext(value); // 구독자쪽에서 전달받은 데이터를 출력
						if(cnt == 2) {
							TimeUtils.sleep(2000L);
							request(2); // 1개의 데이터 요청
							cnt = 0;
						}
					}
				});
	}
}