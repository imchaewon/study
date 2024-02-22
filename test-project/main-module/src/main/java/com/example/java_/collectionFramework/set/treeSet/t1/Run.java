package com.example.java_.collectionFramework.set.treeSet.t1;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Run {
	public static void main(String[] args) {
		Set<Integer> treeSet = new TreeSet<>(Arrays.asList(4,3,2,1,1,3,3,4));
		System.out.println(treeSet);
	}
}
