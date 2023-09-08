package com.example.java_.thread.raceCondition.lock.t1;

public class Run {
	public static void main(String[] args) {

		useLock();
		notUseLock();
	}

	private static void useLock() {
		Counter counter = new Counter();

		Thread increase = new Thread(() -> {
			for (int i=0;i<1000;i++)
				counter.increment();
		});

		Thread decrease = new Thread(() -> {
			for (int i=0;i<1000;i++)
				counter.decrement();
		});

		increase.start();
		decrease.start();

		System.out.println(counter.getCount());
	}

	private static void notUseLock() {
		Counter2 counter = new Counter2();

		Thread increase = new Thread(() -> {
			for (int i=0;i<1000;i++)
				counter.increment();
		});

		Thread decrease = new Thread(() -> {
			for (int i=0;i<1000;i++)
				counter.decrement();
		});

		increase.start();
		decrease.start();

		System.out.println(counter.getCount());
	}
}
