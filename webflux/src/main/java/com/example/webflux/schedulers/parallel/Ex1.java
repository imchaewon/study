package com.example.webflux.schedulers.parallel;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

public class Ex1 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel()
                .subscribe(Logger::onNext);
    }
}