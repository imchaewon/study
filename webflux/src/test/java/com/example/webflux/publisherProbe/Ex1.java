package com.example.webflux.publisherProbe;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class Ex1 {
    @Test
    public void publisherProbeTest(){
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower());

        StepVerifier
                .create(PublisherProbeExample.processWith(PublisherProbeExample.useMainPower(), probe.mono()))
                .expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }

    public class PublisherProbeExample {
        public static Mono<String> processWith(Mono<String> main, Mono<String> standby) {
            return main
                    .flatMap(message -> Mono.just(message))
                    .switchIfEmpty(standby);
        }

        public static Mono<String> useMainPower() {
            return Mono.empty();
        }

        public static Mono<String> useStandbyPower() {
            return Mono.just("# use Standby Power");
        }
    }
}