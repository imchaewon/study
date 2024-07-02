package com.example.webflux.programmatic;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class SinksOneEx2 {
    public static void main(String[] args) {
        // emit된 데이터 중에서 단 하나의 데이터만 구독자에게 전달함. 나머지 데이터는 Drop됨
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", FAIL_FAST);

        // Sinks.One은 단 한개의 데이터를 emit할 수 있기 때문에 두번째 emit한 데이터는 drop됨
        sinkOne.emitValue("Hi Reactor", FAIL_FAST);

        mono.subscribe(data -> Logger.onNext("Subscriber1 ", data));
        mono.subscribe(data -> Logger.onNext("Subscriber2 ", data));
    }
}