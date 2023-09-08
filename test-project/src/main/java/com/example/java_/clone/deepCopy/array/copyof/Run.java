package com.example.java_.clone.deepCopy.array.copyof;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[] arr = {1,2,3};
		int[] arr2 = Arrays.copyOf(arr, arr.length -1);
//		arr2[2] = 4;

		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr));

	}
}
