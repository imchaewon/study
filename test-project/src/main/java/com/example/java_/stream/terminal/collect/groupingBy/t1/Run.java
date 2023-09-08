package com.example.java_.stream.terminal.collect.groupingBy.t1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		String s1 = "asdfaasdghdsasdafd";

		System.out.println(Arrays.stream(s1.split("")).collect(Collectors.groupingBy(s -> s)));
		System.out.println(s1.chars().mapToObj(i->(char)i).collect(Collectors.groupingBy(s -> s)));
		System.out.println(s1.chars().mapToObj(i->(char)i).collect(Collectors.groupingBy(s -> s)).entrySet());
		System.out.println(Arrays.toString(s1.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(s -> s)).values().toArray()));
		System.out.println(Arrays.toString(s1.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(s -> s)).keySet().toArray()));
		System.out.println(s1.chars().mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(s -> s))
				.entrySet()
				.stream()
				.filter(i -> i.getValue().size() == 1)
				.map(Map.Entry::getKey)
				.map(String::valueOf)
				.sorted()
				.collect(Collectors.joining()));
		System.out.println(Arrays.stream(s1.split(""))
				.collect(Collectors.groupingBy(s -> s))
				.entrySet()
				.stream()
				.filter(i -> i.getValue().size() == 1)
				.map(Map.Entry::getKey)
				.sorted()
				.collect(Collectors.joining()));

	}
}
