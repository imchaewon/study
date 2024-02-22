package com.example.java_.stream.intermediate.filter.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Run {
    public static void main(String[] args) {
    	distinct();
    	distinct2();
    }

    /**
     * 중복 필터링
     */
    static void distinct() {
        /* 내부반복 */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        numbers.stream()
                .filter(i -> i % 2 == 0) // 조건에 맞는 데이터만 가져옴
                .distinct() /* 중복 필터링 */
                .forEach(System.out::println);
    }

    static void distinct2() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        Stream<Integer> s = numbers.stream().filter(a -> a * 2 >= 6).distinct().collect(null);

        System.out.println(s);


    }
}
