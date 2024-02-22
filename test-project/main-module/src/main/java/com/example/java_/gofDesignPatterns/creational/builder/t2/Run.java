package com.example.java_.gofDesignPatterns.creational.builder.t2;

public class Run {
	public static void main(String[] args) {

		Bicycle bicycle = Bicycle.builder().build();
		Bicycle.BicycleBuilder bicycleBuilder = Bicycle.builder().wheel(2).company("메리다").price(10000);

		System.out.println(bicycle);
		System.out.println(bicycleBuilder);

		bicycleBuilder.price(20000);

	}
}
