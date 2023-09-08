package com.example.java_.thread.t3;

// 쓰레드 실행 후 종료 하는 예제
public class Sample extends Thread{
	int seq;

	public Sample(int seq) {
		this.seq = seq;
	}

	@Override
	public void run() {
		System.out.println(this.seq + "쓰레드 시작");
		try {
			Thread.sleep(1000); // 1초 대기함
		} catch (Exception e) {
		}
		System.out.println(this.seq + "쓰레드 종료");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) { // 총 10개의 쓰레드를 생성하여 실행함
			Thread t = new Sample(i);
			t.start();
		}
		System.out.println("메인 메소드 종료");
	}
}
