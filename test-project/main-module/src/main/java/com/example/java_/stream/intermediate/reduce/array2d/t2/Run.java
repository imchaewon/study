package com.example.java_.stream.intermediate.reduce.array2d.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[][] arrs = {new int[]{1,2,3}, new int[]{10,11,12}};

		System.out.println(Arrays.toString(m1(arrs)));

	}

	private static int[] m1(int[][] arrs) {
		return Arrays.stream(arrs).reduce(((ints, ints2) -> new int[]{
				Arrays.stream(ints).sum(), Arrays.stream(ints2).sum()
		})).orElse(new int[]{});
	}
}
