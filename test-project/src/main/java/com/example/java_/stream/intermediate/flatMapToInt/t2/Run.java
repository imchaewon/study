package com.example.java_.stream.intermediate.flatMapToInt.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[][] arr = new int[][]{
				new int[]{1, 2, 3},
				new int[]{4, 5}
		};

		System.out.println(Arrays.deepToString(arr));

		System.out.println(Arrays.toString(m1(arr)));

	}

	private static int[] m1(int[][] arr) {
		return Arrays.stream(arr).flatMapToInt(Arrays::stream).toArray();
	}
}
