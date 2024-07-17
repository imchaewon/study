package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Ex5 {
    public static void main(String[] args) {
        String key1 = "id";
        Mono<String> mono =
            Mono.deferContextual(contextView ->
                Mono.just("ID: " + contextView.get(key1))
            )
            .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "village"))
                .subscribe(e -> Logger.onNext("subscriber 1", e));

        mono.contextWrite(context -> context.put(key1, "itWorld"))
                .subscribe(e -> Logger.onNext("subscriber 2", e));

        TimeUtils.sleep(100);
    }
}