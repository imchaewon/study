package com.example.java_.collectionFramework.set.linkedHashSet.t1;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Run {
	public static void main(String[] args) {
		Set<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(4,2,3,1,1,3,3,4));
		System.out.println(linkedHashSet);
	}
}
