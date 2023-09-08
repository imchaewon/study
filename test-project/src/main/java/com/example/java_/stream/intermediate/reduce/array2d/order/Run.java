package com.example.java_.stream.intermediate.reduce.array2d.order;

import java.util.Arrays;
import java.util.Comparator;

public class Run {
	public static void main(String[] args) {

		int[][] arrs = new int[][]{
				{2,6},
				{1,4},
				{2,3},
		};

		System.out.println(Arrays.deepToString(arrs));

		Arrays.sort(arrs, Comparator.comparing(o1 -> o1[0]));

		System.out.println(Arrays.deepToString(arrs));

		arrs = new int[][]{
				{2,6},
				{1,4},
				{2,3},
		};

		Arrays.sort(arrs, (o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));

		System.out.println(Arrays.deepToString(arrs));

	}
}
