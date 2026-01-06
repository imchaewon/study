package com.example.java_._codingTest.beakjoon;

import java.util.*;

public class S7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			list.add(x);
			Collections.sort(list);
		}
		System.out.println("list = " + list);
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j <= i; j++) {
				result += list.get(j);
			}
		}
		System.out.println(result);
	}
}