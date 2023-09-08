package com.example.java_.thread.t4;

import java.util.ArrayList;

// 쓰레드 실행후 메인메소드가 종료 된 후 종료시키는 예제
public class Sample extends Thread{
	int seq;

	public Sample(int seq) {
		this.seq = seq;
	}

	public void run() {
		System.out.println(this.seq + "쓰레드 시작");
		try {
			Thread.sleep(1000);
		} catch (Exception ignored) {
		}
		System.out.println(this.seq + "쓰레드 종료");
	}

	public static void main(String[] args) {

		ArrayList<Thread> threads = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Thread t = new Sample(i);
			t.start();
			threads.add(t);
		}

		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			try {
				t.join(); // 쓰레드가 종료될 때까지 기다림
			} catch (Exception e) {
			}
		}

		System.out.println("메인 메소드 종료");

	}
}
