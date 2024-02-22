package com.example.java_.collectionFramework.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("가","나","다","라","마");
		System.out.println(list);
		Collections.rotate(list, 2);
		System.out.println(list);
		Collections.rotate(list, -2);
		System.out.println(list);

		System.out.println("\nswap..");
		System.out.println(list);
		Collections.swap(list,2,3);
		System.out.println(list);
	}
}
