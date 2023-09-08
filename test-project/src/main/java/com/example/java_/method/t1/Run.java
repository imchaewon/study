package com.example.java_.method.t1;

public class Run {
	public static void main(String[] args) {

		m1();

	}

	private static void m1() {

		m2();

		Run r = new Run();
		r.m3();

	}

	public static void m2() {
		System.out.println("m2");
	}

	public void m3() {
		System.out.println("m3");
		m4();
	}

	public void m4() {
		System.out.println("m4");
	}

}
