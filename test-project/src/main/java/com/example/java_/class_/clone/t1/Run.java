package com.example.java_.class_.clone.t1;

public class Run {
	public static void main(String[] args) throws CloneNotSupportedException {

		C1 c1 = new C1("김준수",11);
		System.out.println(c1);

		C1 c2 = c1.clone();
		System.out.println(c2);

		c2.name = "유아인";

		System.out.println(c1);
		System.out.println(c2);

	}
}
