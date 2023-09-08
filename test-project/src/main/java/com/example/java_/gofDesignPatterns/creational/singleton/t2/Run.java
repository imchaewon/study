package com.example.java_.gofDesignPatterns.creational.singleton.t2;

public class Run {
	public static void main(String[] args) {

		Car car = Car.getInstance();
		if (car.isEnableUseCar()){
			car.drive();
//			car.parking();
		}

		Car car2 = Car.getInstance();
		if (car.isEnableUseCar()){
			car.drive();
		}else{
			System.out.println("wait");
		}

	}
}
