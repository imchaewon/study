package com.example.java_.class_.abstract_.t5.객체지향적;

import com.example.java_.class_.abstract_.t5.Point;

public class Square implements Shape{
	private Point topLeft;
	private double side;

	@Override
	public double area() {
		return side * side;
	}
}