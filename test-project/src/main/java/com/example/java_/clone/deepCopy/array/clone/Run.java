package com.example.java_.clone.deepCopy.array.clone;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		int[] arr2 = arr.clone();

		arr2[2] = 1;

		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr2));
	}
}
