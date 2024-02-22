package com.example.java_.thread.raceCondition.synchronized_.t1;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			int finalI = i;
			Thread thread = new Thread(() -> {
//				// 리스트 객체에 락을 걸어서 한 번에 하나의 스레드만이 해당 블록에 접근할 수 있도록 함. 리스트에 접근하는 모든 코드 블록을 synchronized 블록으로 감싸야 함
//				synchronized (list) {
					for (int j = 100; j < 200; j++)
						list.add(finalI + ": " + j);
//				}
			});
			threads.add(thread);
			thread.start();
		}

		// 모든 쓰레드의 종료를 기다림
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("list = " + list);
		System.out.println("list.size() = " + list.size());
	}
}
