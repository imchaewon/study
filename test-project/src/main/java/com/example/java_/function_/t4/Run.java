package com.example.java_.function_.t4;

import java.util.function.Function;

public class Run {
	public static void main(String[] args) {

		Function<Integer,Double> func1 = i -> i + 2.0;

		Function<Double,Double> func2 = d -> d * 10;

		System.out.println(func2.apply(func1.apply(2)));

		System.out.println(func1.andThen(func2).apply(2));

		Function<Integer,Double> func3 = func2.compose(func1);

		System.out.println(func3.apply(2));


	}
}
