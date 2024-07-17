package com.example.webflux.debuging.checkpoint;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

public class Ex3 {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("CheckpointEx5.zipWith.checkpoint", true)
                .map(e -> e + 2)
                .checkpoint("CheckpointEx5.map.checkpoint", true)
                .subscribe(Logger::onNext, Logger::onError);
    }
}