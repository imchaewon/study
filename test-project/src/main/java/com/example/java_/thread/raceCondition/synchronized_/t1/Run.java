package com.example.java_.thread.raceCondition.synchronized_.t1;

public class Run {
	public static void main(String[] args) {

		Counter counter = new Counter();
		Counter2 counter2 = new Counter2();

		Thread incrementThread = new Thread(() -> {
			for (int i=0;i<1000;i++){
				counter.increment();
				counter2.increment();
			}
		});

		Thread decrementThread = new Thread(() -> {
			for (int i=0;i<1000;i++){
				counter.decrement();
				counter2.decrement();
			}
		});

		incrementThread.start();
		decrementThread.start();

		System.out.println("Final Count: " + counter.getCount());
		System.out.println("Final Count2: " + counter2.getCount());

	}
}
