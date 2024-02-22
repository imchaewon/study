package com.example.java_.stream.intermediate.reduce.array2d.t1;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[][] arrs = {new int[]{1,2,3}, new int[]{10,11,12}};

		System.out.println(Arrays.toString(m1(arrs)));
		System.out.println(Arrays.toString(m2(arrs)));

	}

	private static int[] m1(int[][] arrs) {
		return Arrays.stream(arrs).reduce((ints, ints2) -> new int[]{
				ints[0] + ints2[0],
				ints[1] + ints2[1],
				ints[2] + ints2[2],
		}).orElse(new int[]{});
	}

	private static int[] m2(int[][] arrs) {
		return Arrays.stream(arrs).reduce((ints, ints2) -> {
			int[] ints1 = new int[arrs[0].length];
			for (int i = 0; i < arrs[0].length; i++)
				ints1[i] = ints[i] + ints2[i];
			return ints1;
		}).orElse(new int[]{});
	}
}
