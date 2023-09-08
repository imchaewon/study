package com.example.java_.stream.terminal.collect.joining.t2;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		System.out.println(IntStream.range(0,5).mapToObj(String::valueOf).collect(Collectors.joining()));

	}
}
