package com.example.java_.thread.t7;

public class Run {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
//			C1 c1 = new C1();
//			c1.num = i;
//			c1.run();

			C1 c1 = new C1();
			c1.num = i;
			Runnable runnable = c1;
			Thread t = new Thread(runnable);
			t.start();


		}




	}
}
