package com.example.java_.collectionFramework.list.arrayList.t4;

import java.util.*;

public class Run {
	public static void main(String[] args) {

		Integer[] arr = {1,2,3,4};

		List<Integer> list = Arrays.asList(arr);
		System.out.println("list1: " + list);

		List<Integer> list2 = new ArrayList<>(list);
		list2.add(5);
		System.out.println("list2: " + list2);

		List<Integer> list3 = new ArrayList<>();
		list3.add(0);
		list3.addAll(list);
		System.out.println("list3: " + list3);

		List<Integer> list4 = new ArrayList<>();
		list4.add(0);
		list4.addAll(Arrays.asList(arr));
		System.out.println("list4: " + list4);

		Set<Integer> set1 = new HashSet<Integer>();
		set1.add(10);
		set1.add(11);
		set1.add(12);
		System.out.println("set1: " + set1);

		List<Integer> list5 = new ArrayList<>();
		list5.add(0);
		list5.addAll(set1);
		System.out.println("list5: " + list5);



	}
}
