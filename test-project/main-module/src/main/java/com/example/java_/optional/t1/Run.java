package com.example.java_.optional.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Run {
	public static void main(String[] args) {

		List<Obj1> list = Arrays.asList(
			new Obj1("aaa",10, Gender.FEMAIL),
			new Obj1("bbb", Gender.MAIL),
			new Obj1("ccc",10)
		);

		List<Obj2> list2 = new ArrayList<>();
		list.forEach(item -> {
			Obj2 o2 = new Obj2();
			o2.setGender(Optional.ofNullable(item.getGender()).orElse(Gender.TRANSGENDER));
			list2.add(o2);
		});

		List<Obj3> list3 = new ArrayList<>();
		list.forEach(item -> {
			Obj3 o3 = new Obj3();
			o3.setAge(Optional.ofNullable(item.getAge()).map(i -> i + "").orElse("99"));
			list3.add(o3);
		});


		System.out.println(list);
		System.out.println(list2);
		System.out.println(list3);

	}
}
