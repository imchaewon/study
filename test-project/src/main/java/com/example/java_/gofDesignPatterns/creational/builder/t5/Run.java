package com.example.java_.gofDesignPatterns.creational.builder.t5;

public class Run {
	public static void main(String[] args) {

		C1 o1 = new C1();

		C1 o2 = C1.builder().name("aa").age(11).build();

		System.out.println(o1);
		System.out.println(o2);


	}
}
