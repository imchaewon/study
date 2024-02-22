package com.example.java_.stream.intermediate.flatMap.t3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		String[][] arr = new String[][]{
				new String[]{"a","b","c"},
				new String[]{"d","e"}
		};

		System.out.println(Arrays.deepToString(arr));

		System.out.println(m1(arr));

	}

	private static List<String> m1(String[][] arr) {
		return Arrays.stream(arr).flatMap(a -> Arrays.stream(a).map(e -> e+"aa")).collect(Collectors.toList());
	}
}
