package com.example.java_.class_.anonymousClass.t6;

interface IAnimal{
	public String bark();
	public String jump();
}

public class Run {
	public static void main(String[] args) {

		IAnimal cat = new IAnimal() {
			@Override
			public String bark() {
				return "야옹";
			}

			@Override
			public String jump() {
				return "폴짝";
			}
		};

		System.out.println(cat.bark());
		System.out.println(cat.jump());
	}
}
