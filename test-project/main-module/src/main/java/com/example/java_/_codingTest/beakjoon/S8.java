package com.example.java_._codingTest.beakjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class S8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int target = sc.nextInt();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			list.add(sc.nextInt());
		}
		int result = 0;
		for (int x = 0; x < k; x++) {
			int n1 = list.get(x);
			for (int y = 0; y < x; y++) {
				int n2 = list.get(y);
				for (int z = 0; z < y; z++) {
					int n3 = list.get(z);
					int sum = n1 + n2 + n3;
					result = Math.max(result, sum > target ? result : sum);
				}
			}
		}
		System.out.println(result);
	}
}