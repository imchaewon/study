package com.example.webflux.debuging.debugMode;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

public class Ex1 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .subscribe(Logger::doOnNext);
    }
}