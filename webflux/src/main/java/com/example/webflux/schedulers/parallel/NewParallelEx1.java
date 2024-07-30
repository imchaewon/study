package com.example.webflux.schedulers.parallel;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class NewParallelEx1 {
    public static void main(String[] args) {
        Mono<Integer> flux =
                Mono.
                        just(1)
                        .publishOn(Schedulers.newParallel("Parallel Thread", 4, true));

        flux.subscribe(e -> {
            Logger.onNext("subscribe 1 doing", e);
            TimeUtils.sleep(5000);
            Logger.onNext("subscribe 1 done", e);
        });

        flux.subscribe(e -> {
            Logger.onNext("subscribe 2 doing", e);
            TimeUtils.sleep(4000);
            Logger.onNext("subscribe 2 done", e);
        });

        flux.subscribe(e -> {
            Logger.onNext("subscribe 3 doing", e);
            TimeUtils.sleep(3000);
            Logger.onNext("subscribe 3 done", e);
        });

        flux.subscribe(e -> {
            Logger.onNext("subscribe 4 doing", e);
            TimeUtils.sleep(2000);
            Logger.onNext("subscribe 4 done", e);
        });

        TimeUtils.sleep(6000);
    }
}