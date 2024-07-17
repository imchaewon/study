package com.example.webflux.schedulers.parallel;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Ex2 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15})
                .parallel() // 병렬 처리 준비
                .runOn(Schedulers.parallel()) // 병렬 스케줄러 설정
                .subscribe(Logger::onNext);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        	System.out.println("Ex2.main()");
        	e.printStackTrace();
        }
    }
}