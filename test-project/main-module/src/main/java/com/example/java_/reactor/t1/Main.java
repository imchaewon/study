package com.example.java_.reactor.t1;

public class Main {
    public static void main(String[] args) {
        DataProducer producer = new DataProducer();
        DataConsumer consumer = new DataConsumer(producer.getSink());

        consumer.consumeData();

        producer.produceData("Hello, World!");
        producer.produceData("This is a test.");
        producer.produceData("Reactive programming with Reactor!");

        // Optionally, you can complete the sink to signal the end of the stream
        // producer.getSink().tryEmitComplete();
    }
}