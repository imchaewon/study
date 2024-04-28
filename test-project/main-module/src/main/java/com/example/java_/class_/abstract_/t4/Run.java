package com.example.java_.class_.abstract_.t4;

public class Run {
	public static void main(String[] args) {
		Point point = new PointImpl();
		point.setCartesian(3.0, 4.0);
		System.out.println("X coordinate = " + point.getX());
		System.out.println("Y coordinate = " + point.getY());
	}
}