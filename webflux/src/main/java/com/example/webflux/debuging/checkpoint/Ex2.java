package com.example.webflux.debuging.checkpoint;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

public class Ex2 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("CheckpointEx5.zipWith.checkpoint")
                .map(e -> e + 2)
                .checkpoint("CheckpointEx5.map.checkpoint")
                .subscribe(Logger::onNext, Logger::onError);
    }
}