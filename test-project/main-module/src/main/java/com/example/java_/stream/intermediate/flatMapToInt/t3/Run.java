package com.example.java_.stream.intermediate.flatMapToInt.t3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		int[][] arr = new int[][]{
				new int[]{1, 2, 3},
				new int[]{4, 5}
		};

		System.out.println(Arrays.deepToString(arr));

		System.out.println(m1(arr));

	}

	private static List<Integer> m1(int[][] arr) {
		return Arrays.stream(arr).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
	}
}
