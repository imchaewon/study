package com.example.java_.class_.composite.t1;

public class Run {
	public static void main(String[] args) {
		Circle c = new Circle();
		System.out.println(c.p.x);
		System.out.println(c.p.y);
		System.out.println(c.z);
	}
}

class Circle{
//	int x;
//	int y;
	Point p = new Point();
	int z = 3;
}

class Point{
	int x = 1;
	int y = 2;
}