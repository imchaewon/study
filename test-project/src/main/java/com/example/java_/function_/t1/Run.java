package com.example.java_.function_.t1;

import java.util.function.Function;

public class Run {
	public static void main(String[] args) {

		Function<Integer, Integer> func1 = i -> i * i;
		int result = func1.apply(10);
		System.out.println(result);

		Function<String,Integer> func2 = (s) -> {
			s += "asd";
			return s.length();
		};
		System.out.println(func2.apply("asdf"));


	}
}
