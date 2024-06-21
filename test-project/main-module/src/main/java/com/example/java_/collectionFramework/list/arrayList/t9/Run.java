package com.example.java_.collectionFramework.list.arrayList.t9;

import lombok.AllArgsConstructor;

import java.util.List;

public class Run {
	public static void main(String[] args) {

		Clazz c1 = new Clazz(1L, "aa");
		Clazz c2 = new Clazz(2L, "bb");
		Clazz c3 = new Clazz(3L, "cc");
		List<Clazz> list = List.of(
				c1,
				c2,
				c3
		);

		int i1 = list.indexOf(c1);
		int i2 = list.indexOf(c2);
		int i3 = list.indexOf(c3);
		System.out.println("i1 = " + i1);
		System.out.println("i2 = " + i2);
		System.out.println("i3 = " + i3);

	}
}

@AllArgsConstructor
class Clazz {
	Long id;
	String name;
}