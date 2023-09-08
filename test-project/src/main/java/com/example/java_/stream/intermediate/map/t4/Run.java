package com.example.java_.stream.intermediate.map.t4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;

public class Run {
	public static void main(String[] args) {

		Stream<String> fruits = Stream.of("바나나","사과","무등산 수박","딸기","체리");
		List<String> fruits2 = Arrays.asList("바나나","사과","무등산 수박","딸기","체리");

//		long getCount = fruits.count();
		Function<String, Integer> getCount = fruit -> fruit.length();

		Optional<String> result = fruits.map(Object::toString).collect(maxBy(comparing(getCount)));

		System.out.println(getCount);
		System.out.println(result);
		System.out.println(result.orElse("no item"));

		System.out.println("------------------------------");

		System.out.println(fruits2.stream().map(Object::toString).collect(Collectors.toList()));
		System.out.println(fruits2.stream().map(Run::m1).collect(Collectors.toList()));

	}

	public static String m1(String s){
		return s+"aaaa";
	}
}
