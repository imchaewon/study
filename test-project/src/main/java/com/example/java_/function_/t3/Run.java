package com.example.java_.function_.t3;

import java.util.function.Function;

public class Run {
	public static void main(String[] args) {

		Function<Integer,Integer> func1 = i -> i * i;
		Function<Integer,String> func2 = i -> "result: " + i;

		String result = func1.andThen(func2).apply(10);

		System.out.println(result);

	}
}
