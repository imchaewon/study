package com.example.java_.reactor.t1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class DataConsumer {
    private final Flux<String> flux;

    public DataConsumer(Sinks.Many<String> sink) {
        this.flux = sink.asFlux();
    }

    public void consumeData() {
        flux.subscribe(data -> System.out.println("Received data: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Stream completed"));
    }
}