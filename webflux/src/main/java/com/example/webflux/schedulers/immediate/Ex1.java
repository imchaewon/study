package com.example.webflux.schedulers.immediate;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Ex1 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(e -> e > 3)
                .doOnNext(e -> Logger.doOnNext("filter", e))
                .publishOn(Schedulers.immediate())
                .map(e -> e * 10)
                .doOnNext(e -> Logger.doOnNext("map", e))
                .subscribe(Logger::onNext);
        
        try {
        	Thread.sleep(1000);
        } catch (Exception e) {
        	System.out.println("Ex1.main()");
        	e.printStackTrace();
        }
    }
}