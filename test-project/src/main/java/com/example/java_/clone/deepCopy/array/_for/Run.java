package com.example.java_.clone.deepCopy.array._for;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[] arr = {1,2,3};

		int[] arr2 = new int[3];

		for (int i = 0; i < arr.length; i++)
			arr2[i] = arr[i];

		arr2[2] = 4;

		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr));

	}
}
