package com.example.java_.stream.intermediate.flatMap.t2;

import java.util.Arrays;

public class Run {

	public static void main(String[] args) {

		String[][] arr = new String[][]{
				new String[]{"a","b","c"},
				new String[]{"d","e"}
		};

		System.out.println(Arrays.deepToString(arr));

		System.out.println(Arrays.toString(m1(arr)));

	}

	private static String[] m1(String[][] arr) {
		return Arrays.stream(arr).flatMap(Arrays::stream).toArray(String[]::new);
	}
}
