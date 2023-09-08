package com.example.java_.thread.excutor.t2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(1);

		executor.execute(()->{
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("Run.m1()");
					e.printStackTrace();
				}
				System.out.println(i);
			}
		});

	}
}
