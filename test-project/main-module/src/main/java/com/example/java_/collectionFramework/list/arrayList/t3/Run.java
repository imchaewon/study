package com.example.java_.collectionFramework.list.arrayList.t3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
		List<Integer> list2 = list;
		List<Integer> list3 = list.stream().collect(Collectors.toList());
		List<Integer> list4 = new ArrayList<>(list);

		list2.add(4);
		list3.add(5);
		list4.add(6);

		System.out.println(list);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);
	}
}
