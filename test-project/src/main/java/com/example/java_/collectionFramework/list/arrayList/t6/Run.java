package com.example.java_.collectionFramework.list.arrayList.t6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1,2,3,4,2,3,5);

		System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(", ")));

	}
}
