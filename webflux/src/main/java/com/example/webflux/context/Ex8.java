package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Ex8 {
    public static void main(String[] args) {
        String key1 = "id";
        Mono.just("chaewon")
//                .transformDeferredContextual((stringMono, contextView) -> contextView.get("job"))
                .flatMap(e -> Mono.deferContextual(contextView ->
                        Mono.just(contextView.get(key1) + ", " + e)
                                .transformDeferredContextual((mono, contextView2) ->
                                        mono.map(e2 -> e2 + ", " + contextView2.get("job")))
                                .contextWrite(contextView2 -> contextView2.put("job", "Software Engineer"))
                ))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "itVillage"))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(100);
    }
}