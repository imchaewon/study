package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

public class Ex10 {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("aa", "bb");
        Flux<Integer> flux2 = Flux.just(1, 2);

//        Flux<String> flux3 = flux1.concatMap(flux2);
        Flux<Tuple2<String, Integer>> flux3 = flux1.zipWith(flux2);
        flux3.subscribe(Logger::onNext);

    }
}