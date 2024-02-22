package com.example.java_.literal.t1;

public class Run {
	public static void main(String[] args) {
		double d1 = 10.;
		double d2 = .10;
		double d3 = 10;
		double f1 = 10f;
		double f2 = 3.14e3f;
		double d4 = 1e1;
		double d5 = 1e-3;

		System.out.println("d1: "+d1);
		System.out.println("d2: "+d2);
		System.out.println("d3: "+d3);
		System.out.println("f1: "+f1);
		System.out.println("f2: "+f2);
		System.out.println("d4: "+d4);
		System.out.println("d5: "+d5);

		System.out.println(10/3);
		System.out.println(10./3);
		System.out.println(10d/3);
	}
}
