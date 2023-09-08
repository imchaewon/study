package com.example.java_.collectionFramework.list.arrayList.retainAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>(Arrays.asList(2,1,3,4,5));
		List<Integer> l2 = new ArrayList<>(Arrays.asList(1,3,5));

		System.out.println(l1.retainAll(l2)); // l2에 있는요소들만 남기고 나머지는 제거. l1에 변화가 있어서 true반환
		System.out.println(l2.retainAll(l1)); // l1에 있는요소들만 남기고 나머지는 제거 → 제거할게 없음

		System.out.println(l1);
		System.out.println(l2);
	}
}
