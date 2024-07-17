package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Ex7 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.deferContextual(contextView -> Mono.just(contextView.get(key1)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "chaewon"))
                .contextWrite(context -> context.put(key1, "kevin"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100);
    }
}