package com.example.webflux.debuging.debugMode;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class Ex2 {
    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .subscribe(Logger::doOnNext);
    }
}