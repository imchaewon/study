package com.example.webflux.debuging.checkpoint;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

public class Ex4 {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.just(2, 4, 6, 8);
        Flux<Integer> other = Flux.just(1, 2, 3, 0);

        Flux<Integer> multiplySource = divide(source, other).checkpoint();
        Flux<Integer> plusSource = plus(multiplySource).checkpoint();

        plusSource.subscribe(Logger::onNext, Logger::onError);
    }

    private static Flux<Integer> divide(Flux<Integer> source, Flux<Integer> other) {
        return source.zipWith(other, (x, y) -> x / y);
    }

    private static Flux<Integer> plus(Flux<Integer> source) {
        return source.map(e -> e + 2);
    }
}