package com.example.java_.class_.abstract_.t5.객체지향적;

import com.example.java_.class_.abstract_.t5.Point;

public class Rectangle implements Shape{
	private Point topLeft;
	private double width;
	private double height;

	@Override
	public double area() {
		return width * height;
	}
}