package com.example.java_.collectionFramework.list.arrayList.t1;

import java.util.*;

public class Run {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3);
		List<Integer> list2 = new ArrayList<>(list);
		List<Integer> list3 = new ArrayList<>(Arrays.asList(1,2,3));
		List<Integer> list4 = List.of(1,2,3);
		List<Integer> list5 = new ArrayList<>(List.of(1,2,3));

		try {
			list.add(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			list4.add(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		list2.add(4);
		list3.add(4);
		list5.add(4);

		System.out.println("list: "+list);
		System.out.println("list2: "+list2);
		System.out.println("list3: "+list3);
		System.out.println("list4: "+list4);
		System.out.println("list5: "+list5);

		try {
			List<Integer> list6 = List.of(null,2,3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
