package com.example.java_.stream.terminal.match.t1;

import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		System.out.println(IntStream.range(0,10).anyMatch(i -> i % 2 == 0));
		System.out.println(IntStream.range(0,10).allMatch(i -> i % 2 == 0));
		System.out.println(IntStream.range(0,10).noneMatch(i -> i % 2 == 0));

		System.out.println();

		System.out.println(IntStream.range(0,10).anyMatch(i -> i == 1));
		System.out.println(IntStream.range(0,10).map(i -> i * 2 % 100).allMatch(i -> i % 2 == 0));
		System.out.println(IntStream.range(0,10).filter(i -> i / 2 == 123).noneMatch(i -> i % 2 == 0));

	}
}
