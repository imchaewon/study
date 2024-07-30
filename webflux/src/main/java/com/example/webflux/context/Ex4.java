package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Ex4 {
    public static void main(String[] args) {
        String key1 = "id";
        String key2 = "name";
        Mono.deferContextual(contextView ->
                Mono.just(String.format("ID: %s, Name: %s, Job: %s",
                        contextView.get(key1),
                        contextView.get(key2),
                        contextView.getOrDefault("job", "Software Engineer")))
        )
        .publishOn(Schedulers.parallel())
        .contextWrite(context -> context.putAll(Context.of(key2, "Chaewon").readOnly()))
        .contextWrite(context -> context.put(key1, "itVillage"))
        .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);

    }
}