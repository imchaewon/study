package com.example.java_.stream.intermediate.map.mapToInt.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		String s1 = "123454321";

		String[] sarr = s1.split("");

		System.out.println(Arrays.toString(m1(sarr)));

	}

	private static int[] m1(String[] sarr) {
		return Arrays.stream(sarr).mapToInt(Integer::parseInt).toArray();
	}

}
