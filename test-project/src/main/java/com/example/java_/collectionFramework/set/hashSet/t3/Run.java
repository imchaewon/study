package com.example.java_.collectionFramework.set.hashSet.t3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		Set<String> set = new HashSet<>();
		set.add("bb");
		set.add("cc");
		set.add("aaa");
		System.out.println(set);

		Iterator<String> i = set.iterator();
		while (i.hasNext())
			System.out.println(i.next());

		List<String> list = set.stream().sorted().collect(Collectors.toList());

		System.out.println(list);

	}
}
