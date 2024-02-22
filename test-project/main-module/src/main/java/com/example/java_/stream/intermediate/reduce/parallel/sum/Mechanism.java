package com.example.java_.stream.intermediate.reduce.parallel.sum;

import java.util.stream.IntStream;

public class Mechanism {
	public static void main(String[] args) {

		IntStream.rangeClosed(1,10).reduce((left, right) -> {
			System.out.printf("%d %d\r\n", left, right);
			return left + right;
		});

		System.out.println();

		IntStream.rangeClosed(1,10).reduce((left, right) -> {
			System.out.printf("%d %d\r\n", left, right);
			return 0;
		});

		System.out.println();

		IntStream.rangeClosed(1,10).parallel().reduce((left, right) -> {
			System.out.printf("%d %d\r\n", left, right);
			return left + right;
		});

	}
}
