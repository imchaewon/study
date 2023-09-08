package com.example.java_.stream.terminal.find.t1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		System.out.println(IntStream.range(0,100).filter(i -> i * 2 > 5).findAny().orElse(0));
		System.out.println(IntStream.range(0,100).filter(i -> i * 2 > 5).findFirst().orElse(0));

		System.out.println(IntStream.range(0,100).parallel().filter(i -> i * 2 > 5).findAny().orElse(0));
		System.out.println(IntStream.range(0,100).parallel().filter(i -> i * 2 > 5).findFirst().orElse(0));

	}
}
