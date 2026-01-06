package com.example.java_._codingTest.beakjoon;

import java.util.Scanner;

public class S2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int length = sc.nextInt();
		String nums = sc.next();

		int result = 0;
		for (int i = 0; i < length; i++) {
			result += nums.charAt(i) - '0';
		}

		System.out.println(result);
	}
}