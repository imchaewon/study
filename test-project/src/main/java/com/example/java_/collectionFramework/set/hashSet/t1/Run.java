package com.example.java_.collectionFramework.set.hashSet.t1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Run {
	public static void main(String[] args) {
		Set<Integer> hashSet = new HashSet<>(Arrays.asList(4,2,3,1,1,3,3,4));
		System.out.println(hashSet); // 순서 보장이 되지 않음
	}
}
