package com.example.java_.intStream.concat;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		IntStream is1 = IntStream.of(1, 2, 3);
		IntStream is2 = IntStream.of(4, 5, 6);

		System.out.println(Arrays.toString(m1(is1, is2)));

	}

	private static int[] m1(IntStream is1, IntStream is2) {
		return IntStream.concat(is1,is2).toArray();
	}
}
