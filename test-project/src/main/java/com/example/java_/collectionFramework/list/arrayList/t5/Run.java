package com.example.java_.collectionFramework.list.arrayList.t5;

import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		String[] arr = {"가","나","다"};

		List<String> list = Arrays.asList(arr);

		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("list: " + list);

		System.out.println(list.set(1,"바"));

		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("list: " + list);

		System.out.println(arr[1] = "사");

		System.out.println("list: " + Arrays.toString(arr));
		System.out.println("list: " + list);

	}
}
