package com.example.java_.stream.terminal.collect.joining.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {
		m1();
	}

	private static void m1() {
		List<String> list = Arrays.asList("aaa","ccc","bbb");

//		List<String> sList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
		String s1 = list.stream().map(s->s.toUpperCase()).sorted().collect(Collectors.joining());

		System.out.println(s1);

//		System.out.println(sList);
	}
}
