package com.example.webflux.schedulers.newBoundedElastic;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Ex1 {
    public static void main(String[] args) {
        Scheduler scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread");
        Mono<Integer> mono =
                Mono
                        .just(1)
                        .subscribeOn(scheduler);

        Logger.info("# start");

        mono.subscribe(e -> {
            Logger.onNext("subscribe 1 doing", e);
            TimeUtils.sleep(3000);
            Logger.onNext("subscribe 1 done", e);
        });

        mono.subscribe(e -> {
            Logger.onNext("subscribe 2 doing", e);
            TimeUtils.sleep(3000);
            Logger.onNext("subscribe 2 done", e);
        });

        mono.subscribe(e -> {
            Logger.onNext("subscribe 3 doing", e);
        });

        mono.subscribe(e -> {
            Logger.onNext("subscribe 4 doing", e);
        });

        mono.subscribe(e -> {
            Logger.onNext("subscribe 5 doing", e);
        });

        mono.subscribe(e -> {
            Logger.onNext("subscribe 6 doing", e);
        });

//        mono.subscribe(e -> {
//            Logger.onNext("subscribe 7 doing", e);
//        });
    }
}