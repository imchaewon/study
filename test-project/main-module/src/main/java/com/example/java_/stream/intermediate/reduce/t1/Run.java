package com.example.java_.stream.intermediate.reduce.t1;

import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		int[] ints = new int[]{1,2,3,4,5,6,7,8,9,10};
		int sum = IntStream.of(ints).reduce((integer, integer2) -> integer + integer2).orElse(0);
		int sum2 = IntStream.of(ints).reduce(10, (integer, integer2) -> integer + integer2);
		int sum3 = IntStream.of(ints).reduce(10, Integer::sum);
		System.out.println(sum);
		System.out.println(sum2);
		System.out.println(sum3);

	}
}
