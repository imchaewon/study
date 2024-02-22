package com.example.java_.collectionFramework.list.arrayList.sort;

import java.util.*;

public class Run {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(3,2,4);
		list.sort(Integer::compare);
		System.out.println(list);
		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		list.sort(Comparator.comparingInt(o -> o));
		System.out.println(list);
		list.sort((o1, o2) -> o2 - o1);
		System.out.println(list);


		System.out.println();


		list = Arrays.asList(1,3,2);
		Collections.sort(list);
		System.out.println(list);
		Collections.sort(list, Collections.reverseOrder());
		System.out.println(list);


		System.out.println();


		List<List<Integer>> list2 = Arrays.asList(
				Arrays.asList(3, 1),
				Arrays.asList(2, 4),
				Arrays.asList(5, 3),
				Arrays.asList(8, 2)
		);
		System.out.println(list2);
		list2.sort(Comparator.comparing((o) -> o.get(1)));
		System.out.println(list2);
		list2.sort((o1, o2) -> o2.get(1) - o1.get(1));
		System.out.println(list2);

	}
}








