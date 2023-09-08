package com.example.java_.thread.t2;

public class Run {
	public static void main(String[] args) {

		C1 c1 = new C1();
		C2 c2 = new C2();


		c1.start();
		c2.start();

	}
}
