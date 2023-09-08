package com.example.java_.cast.downcast.t2;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		Object o1 = "aa";

		C1 c1 = new C1();

		List<Object> list = new ArrayList<>();

		List<C1> list2 = new ArrayList<>();

//		list = (List<Object>) list2;

		List<C1> list3 = new ArrayList<>();
		list.forEach(i -> {
			list3.add((C1) i);
		});

		System.out.println(list3);

	}
}
