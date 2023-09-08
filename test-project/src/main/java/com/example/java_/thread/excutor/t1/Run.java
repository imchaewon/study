package com.example.java_.thread.excutor.t1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Run {
	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.execute(thread1());

		Future<List<Integer>> future = executor.submit(thread2());
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

	}

	// Runnable은 실행만
	static Runnable thread1(){
		return () -> {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("Run.m1()");
					e.printStackTrace();
				}
				System.out.println(i);
			}
		};
	}

	// Callable은 값 반환이 필요할때
	static Callable<List<Integer>> thread2(){
		return () -> {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					System.out.println("Run.m1()");
					e.printStackTrace();
				}
				list.add(i);
			}
			return list;
		};
	}
}
