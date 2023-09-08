package com.example.java_.clone.cloneable.shallowCopy;

public class Run {
	public static void main(String[] args) {
		Circle c = new Circle(new Point(1,1), 2.0);
		Circle c2 = c.clone();

		c2.p.x = 123;

		System.out.println(c);
		System.out.println(c2);
	}
}
