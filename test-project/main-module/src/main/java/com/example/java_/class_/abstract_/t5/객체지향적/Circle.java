package com.example.java_.class_.abstract_.t5.객체지향적;

import com.example.java_.class_.abstract_.t5.Point;

public class Circle implements Shape{
	private Point center;
	private double radius;
	public final double PI = 3.141592653589793;

	@Override
	public double area() {
		return PI * radius * radius;
	}
}