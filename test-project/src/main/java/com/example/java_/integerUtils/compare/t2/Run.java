package com.example.java_.integerUtils.compare.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int n = 5;

		int[] arr = new int[10];
		for (int i = 0; i < 10; i++)
			arr[i] = (int)(Math.random() * 7) + 4;
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(m1(arr, n)));

	}

	private static int[] m1(int[] arr, int n) {
		return Arrays.stream(arr).boxed().sorted((a, b) -> Integer.compare(Math.abs(a - n), Math.abs(b - n))).mapToInt(Integer::intValue).toArray();
	}

}













