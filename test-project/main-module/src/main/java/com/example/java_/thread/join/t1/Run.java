package com.example.java_.thread.join.t1;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) throws InterruptedException {

		List<Thread> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			C1 c = new C1();
			c.num = i;
			c.start();
			list.add(c);
		}

		System.out.println("끝?");

		for (Thread thread : list) {
			thread.join();
		}


		System.out.println("끝");
	}
}
