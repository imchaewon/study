package com.example.java_.stream.terminal.collect.toList.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {
		m1();
	}

	private static void m1() {
		List<String> list = Arrays.asList("aaa","ccc","bbb");

		list.stream().map(String::toUpperCase).sorted().collect(Collectors.toList()).forEach(System.out::println);
	}
}
