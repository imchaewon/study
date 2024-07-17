package com.example.webflux.flatMap;

import reactor.core.publisher.Mono;

public class Run {
    public static void main(String[] args) {
//        Flux.just(1, 2, 3)
//                .flatMap(e -> Flux.range(e * 10, 2))
//                .subscribe(System.out::println);

//        IntStream.range(1, 5)
//                .flatMap(e -> IntStream.range(1, e))
//                .forEach(System.out::println);

        Mono.just(1)
                .map(num -> Mono.just(num * 2)) // 각 요소를 2배로 변환하는 Mono를 반환
                .subscribe(result -> System.out.println("Result: " + result));
    }
}