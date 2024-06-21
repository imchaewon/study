package com.example.java_.collectionFramework.set.hashSet.t4;

import java.util.HashSet;
import java.util.Set;

public class Run {
	public static void main(String[] args) {

		Set<String> set = new HashSet<>();

		System.out.println(set.add("aaa"));
		System.out.println(set.add(null));
		System.out.println(set.add(null));

		System.out.println("set = " + set);

	}
}