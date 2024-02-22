package com.example.java_.thread.t6;

public class Run {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			C1 c1 = new C1();
			c1.num = i;
//			c1.run();
			c1.start();
		}




	}
}
