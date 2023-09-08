package com.example.java_.gofDesignPatterns.creational.singleton.t2;

public class Car {
	private static final Car car = new Car();

	private Car(){}

	public static Car getInstance(){
		return car;
	}

	private boolean isUse = false;

	public void drive(){
		isUse = true;
		System.out.println("start driving");
	}

	public void parking(){
		isUse = false;
		System.out.println("parking");
	}

	public boolean isEnableUseCar(){
		return !isUse;
	}

}








