package com.example.java_;

import static java.lang.Thread.sleep;

public class 걍테스트 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("main start");
		Thread thread1 = new Thread(() -> {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println(123);
		});
		thread1.start();
		thread1.join();
		System.out.println("main end");
	}
}