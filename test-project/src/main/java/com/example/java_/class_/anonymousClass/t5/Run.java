package com.example.java_.class_.anonymousClass.t5;

import com.example.java_.class_.anonymousClass.Animal;

public class Run {
	public static void main(String[] args) {

		Animal dog = new Animal(){

		};

		Animal cat = new Animal(){

		};

		System.out.println(dog.getClass().getName());
		System.out.println(cat.getClass().getName());


		Run r = new Run();
		System.out.println(r.getClass().getName());

	}
}
