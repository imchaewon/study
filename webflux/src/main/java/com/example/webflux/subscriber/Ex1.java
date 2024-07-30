package com.example.webflux.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

public class Ex1 {
    public static void main(String[] args) {
        // Publisher
        Mono<String> mono = Mono.just("Hello, World!");

        // Subscriber 정의
        Subscriber<String> subscriber = new Subscriber<>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                // Subscription 저장 및 요청 처리
                this.subscription = s;
                this.subscription.request(1); // 데이터 1개 요청
            }

            @Override
            public void onNext(String s) {
                System.out.println("데이터 받음: " + s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("에러 발생: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("완료됨");
            }
        };

        mono.subscribe(subscriber);
    }
}