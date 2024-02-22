package com.example.java_.enum_.t5;

public class Run {
	public static void main(String[] args) {
		System.out.println("bus fare: " + Transportation.BUS.fare(100));
		System.out.println("bus fare: " + Transportation.TRAIN.fare(100));
		System.out.println("bus fare: " + Transportation.SHIP.fare(100));
		System.out.println("bus fare: " + Transportation.AIRPLANE.fare(100));
	}
}
