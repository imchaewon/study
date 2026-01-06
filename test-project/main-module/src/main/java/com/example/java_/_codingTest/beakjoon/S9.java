package com.example.java_._codingTest.beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S9 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());

		List<Integer> list = new ArrayList<>(k);
		StringTokenizer st = null;

		while (list.size() < k) {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			list.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(list);
		int size = list.size();
		int cutting = (int) Math.round(size * .3 / 2);

		List<Integer> truncatedList = list.subList(cutting, list.size() - cutting);
		double v = truncatedList.stream()
			.mapToInt(Integer::intValue)
			.average()
			.orElse(0.0);
		System.out.println(Math.round(v));
	}
}