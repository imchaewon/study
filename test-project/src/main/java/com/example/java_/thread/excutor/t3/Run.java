package com.example.java_.thread.excutor.t3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run {
	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(1);

		Future<List<Integer>> future = executor.submit(() -> {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				list.add(i);
			}
			return list;
		});

		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

	}
}
