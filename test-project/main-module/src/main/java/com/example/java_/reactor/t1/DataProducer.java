package com.example.java_.reactor.t1;

import reactor.core.publisher.Sinks;

public class DataProducer {
    private final Sinks.Many<String> sink;

    public DataProducer() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    public void produceData(String data) {
        Sinks.EmitResult result = sink.tryEmitNext(data);
        if (result.isFailure()) {
            System.out.println("Failed to emit data: " + result);
        }
    }

    public Sinks.Many<String> getSink() {
        return sink;
    }
}