package com.example.webflux.schedulers.single;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Ex1 {
    public static void main(String[] args) {
        doTask("task1")
                .subscribe(Logger::onNext);

        doTask("tesk2")
                .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.single())
                .filter(e -> e > 3)
                .doOnNext(e -> Logger.doOnNext(taskName, "filter", e))
                .map(e -> e * 10)
                .doOnNext(e -> Logger.doOnNext(taskName, "map", e));
    }
}