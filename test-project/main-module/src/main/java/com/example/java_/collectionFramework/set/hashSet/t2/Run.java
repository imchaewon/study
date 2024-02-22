package com.example.java_.collectionFramework.set.hashSet.t2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Run {
	public static void main(String[] args) {

		Set<Integer> set = new HashSet<>(){
			{
				add(1);
				add(2);
			}
		};
		Set<Integer> set2 = new HashSet<>(Arrays.asList(1,2));

		System.out.println(set);
		System.out.println(set2);

	}
}
