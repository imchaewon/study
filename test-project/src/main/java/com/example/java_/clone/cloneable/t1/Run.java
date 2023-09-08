package com.example.java_.clone.cloneable.t1;

public class Run {
	public static void main(String[] args) {
		C1 c = new C1();
		c.name = "포도";
		c.age = 12;

		C1 c2 = c.clone();
		c2.name = "감자";

		System.out.println(c);
		System.out.println(c2);
	}
}
