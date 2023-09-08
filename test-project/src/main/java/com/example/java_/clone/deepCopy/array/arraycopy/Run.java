package com.example.java_.clone.deepCopy.array.arraycopy;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[] arr = {1,2,3};
		int[] arr2 = new int[3];
		System.arraycopy(arr, 0, arr2, 0, arr.length);
		arr2[2] = 4;

		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr));

	}
}
