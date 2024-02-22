package com.example.java_.gofDesignPatterns.creational.builder.t4;

public class Run {
	public static void main(String[] args) {

		C1.C1Builder builder = C1.builder();
		builder.servingSize(200);
		builder.servings(5);
		C1 o1 = builder.build();

		C1 o2 = new C1.C1Builder().servingSize(200).build();

		C1 o3 = C1.builder().carbohydrate(99).build();

		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);


	}
}
