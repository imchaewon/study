package com.example.java_.intStream.t2;

import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		// 1~10까지
		IntStream is1 = IntStream.rangeClosed(1,10);
		is1.forEach(System.out::println);

		System.out.println();

		// 1~100까지 합
		is1 = IntStream.rangeClosed(1,100);
		int i1 = is1.sum();
		System.out.println("1~100까지 합: " + i1);

		// 배열의 합
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		is1 = IntStream.of(arr);
		int i2 = is1.sum();
		System.out.println("배열의 합: " + i2);

		// 1부터 10까지의 수 중 홀수의 합
		is1 = IntStream.of(arr);
		System.out.println("홀수의 합: " + is1.filter(i -> i % 2 == 1).sum());

	}
}
