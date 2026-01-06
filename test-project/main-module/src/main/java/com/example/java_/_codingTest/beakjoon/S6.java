package com.example.java_._codingTest.beakjoon;

import java.util.Scanner;
import java.util.Stack;

public class S6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stack = new Stack<>();
		int K = sc.nextInt();
		for (int i = 0; i < K; i++) {
			int a = sc.nextInt();
			if (a != 0) {
				stack.push(a);
			} else {
				stack.pop();
			}
		}
		System.out.println(stack.stream().mapToInt(Integer::intValue).sum());
	}
}