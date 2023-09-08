package com.example.java_.interface_.t5;

public class VehicleExample {
	public static void main(String[] args) {
		Vehicle vehicle = new Bus();

		vehicle.run();

		Bus bus = (Bus) vehicle;

		bus.run();
		bus.checkFare();

		System.out.println("-------");

		Driver d = new Driver();

		d.drive(vehicle);


	}
}
