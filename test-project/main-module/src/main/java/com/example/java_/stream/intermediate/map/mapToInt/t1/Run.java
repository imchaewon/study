package com.example.java_.stream.intermediate.map.mapToInt.t1;

import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1,2,3,4,5);

		System.out.println(list.stream().mapToInt(Integer::intValue).sum());
		System.out.println(list.stream().reduce((i, j) -> i + j).orElse(0));
		System.out.println(list.stream().max(Integer::compareTo).orElse(0));
		System.out.println(list.stream().mapToInt(Integer::intValue).max().orElse(0));

	}
}
