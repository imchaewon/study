package com.example.java_.thread.t1;

public class Sample extends Thread{
	public void run() { //쓰레드를 상속하면 run 메소드를 구현하기
		System.out.println("Thread Run..");
	}

	public static void main(String[] agrs) {
		Sample s = new Sample();
		s.start();
	}
}
