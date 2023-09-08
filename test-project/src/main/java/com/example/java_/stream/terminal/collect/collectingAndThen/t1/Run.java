package com.example.java_.stream.terminal.collect.collectingAndThen.t1;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		int i1 = IntStream.range(0, 5).boxed().collect(Collectors.collectingAndThen(Collectors.toSet(), c -> Math.min(c.size(), 10)));

		System.out.println(i1);

	}
}
