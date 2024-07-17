package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Ex6 {
    public static void main(String[] args) {
        String key1 = "id";
        String key2 = "name";

        Mono.deferContextual(contextView -> Mono.just(contextView.get(key1)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "chaewon"))
                .transformDeferredContextual((mono, contextView) ->
                        mono.map(e -> e + ", " + contextView.getOrDefault(key2, "Tom"))
                )
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100);
    }
}