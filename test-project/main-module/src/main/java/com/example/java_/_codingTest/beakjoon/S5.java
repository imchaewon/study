package com.example.java_._codingTest.beakjoon;

import java.util.Scanner;

public class S5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int result = 0;

//		4,6
		int gcd = gcd(a, b);
		int lcm = lcm(a, b);
		System.out.println(gcd);
		System.out.println(lcm);
	}

	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}
}