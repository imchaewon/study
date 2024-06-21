package com.example.java_.groupingByConcurrent.t1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		List<String> words = Arrays.asList("apple", "banana", "orange", "grape", "pear", "melon");

		Map<Integer, List<String>> lengthGroups = words.parallelStream()
				.collect(Collectors.groupingByConcurrent(String::length));

		System.out.println(lengthGroups);

	}
}