package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Ex2 {
    public static void main(String[] args) {
        String key1 = "id";
        String key2 = "name";
        Mono<String> mono = Mono.deferContextual(contextView ->
                        Mono.just("ID: " + contextView.get(key1) + ", " + "Name: " + contextView.get(key2))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(Context.of(key1, "itVillage", key2, "Chaewon"));

        mono.subscribe(e -> Logger.onNext(e));

        TimeUtils.sleep(100);

    }
}