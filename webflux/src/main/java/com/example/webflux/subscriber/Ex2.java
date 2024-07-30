package com.example.webflux.subscriber;

import com.example.webflux.utils.Logger;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

public class Ex2 {
    public static void main(String[] args) {
//        Flux<Integer> flux1 = Flux.just(1, 2);
//        flux1.doOnNext(Logger::onNext);
//        flux1.subscribe();

//        Flux<Integer> flux1 = Flux.just(1, 2);
//        Flux<Integer> newFlux1 = flux1.doOnNext(Logger::onNext);
//        newFlux1.subscribe();

//        Flux<Integer> flux1 = Flux.just(1, 2);
//        flux1.doOnNext(Logger::onNext)
//                .subscribe();

        Flux.just(1, 2)
                .doOnNext(Logger::onNext)
                .subscribe();
    }
}