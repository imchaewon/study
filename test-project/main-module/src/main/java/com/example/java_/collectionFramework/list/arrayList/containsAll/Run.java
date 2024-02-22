package com.example.java_.collectionFramework.list.arrayList.containsAll;

import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(2,1,3,4,5);
		List<Integer> l2 = Arrays.asList(1,3,5);

		System.out.println(l1.containsAll(l2));
		System.out.println(l2.containsAll(l1));
	}
}
