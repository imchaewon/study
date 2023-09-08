package com.example.java_.thread.t5;

import java.time.LocalTime;

public class Run {
	public static void main(String[] args) {

		Thread thread = new Thread(
			() -> {
				System.out.println("Runnable at " + LocalTime.now());
			}
		);
		thread.start();

	}
}
