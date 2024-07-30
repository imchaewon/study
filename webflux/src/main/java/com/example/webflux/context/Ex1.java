package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Ex1 {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> mono = Mono.deferContextual(context ->
                        Mono.just("Hello" + " " + context.get(key)).doOnNext(Logger::doOnNext)
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual((mono2, context) -> mono2.map(data -> data + " " + context.get(key)))
                .contextWrite(context -> context.put(key, "Reactor"));

        mono.subscribe(e -> Logger.onNext(e));

        TimeUtils.sleep(100);
    }
}