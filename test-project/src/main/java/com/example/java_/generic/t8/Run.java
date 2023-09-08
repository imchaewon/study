package com.example.java_.generic.t8;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		AnimalList<Animal> list = new AnimalList<>();

		list.add(new Dog());
		list.add(new Cat());

		for (int i = 0; i < list.size(); i++) {
			list.get(i).m1();
		}

		System.out.println(list.get(0).i1);
		System.out.println(list.get(1).i1);

		Dog d = new Dog();
		System.out.println(d.i1);
		d.m2();

		list.add(new Animal());
		list.get(2).m2();

		List<Dog> listtt = new ArrayList<>();
		m3(listtt);

		List<String> listtt2 = new ArrayList<String>();
//		m3(listtt2); Animal을 상속받지않아서 안됨

	}

	public static void m3(List<? extends Animal> list) {

//		list.add(new Dog());
	}
}
