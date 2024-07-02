package com.example.webflux.programmatic;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class SinksEx1 {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<String> fluxView = unicastSink.asFlux();

        IntStream
                .range(1, tasks)
                .forEach(n -> {
                    new Thread(() -> {
                        unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST);
                        log.info("# emitted: {}", n);
                    }).start();
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        fluxView
//                .publishOn(Schedulers.parallel())
//                .map(result -> result + " success!")
//                .doOnNext(n -> log.info("# map(): {}", n))
//                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    private static String doTask(int taskNumber) {
        return "task " + taskNumber + " result";
    }
}