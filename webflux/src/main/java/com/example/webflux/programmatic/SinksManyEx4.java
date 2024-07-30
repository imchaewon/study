package com.example.webflux.programmatic;

import com.example.webflux.utils.Logger;
import com.example.webflux.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class SinksManyEx4 {
    public static void main(String[] args) {
        Sinks.Many<Integer> replaySink = Sinks.many().replay().all();
        Flux<Integer> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext("Subscriber1", data));
        fluxView.subscribe(data -> Logger.onNext("Subscriber2", data));
    }
}