package com.example.java_.stream.intermediate.reduce.t2;

import java.util.Optional;
import java.util.stream.Stream;

public class Run {
	public static void main(String[] args) {

		Stream<Integer> numbers = Stream.of(1,2,3,4,5,6,7,8,9,10);
		Optional<Integer> sum = numbers.reduce((i, j) -> i + j);
		Optional<Integer> sum2 = numbers.reduce(Integer::sum);
		sum.ifPresent(System.out::println);

	}
}
