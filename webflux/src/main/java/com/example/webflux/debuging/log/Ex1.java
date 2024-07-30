package com.example.webflux.debuging.log;

import com.example.webflux.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

public class Ex1 {
    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "ZXCVZXCVXCVXVC", "GRAPES"})
                .log()
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .map(fruits::get)
                .subscribe(Logger::onNext, Logger::onError);
    }
}