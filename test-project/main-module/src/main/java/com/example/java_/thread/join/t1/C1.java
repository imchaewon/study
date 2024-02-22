package com.example.java_.thread.join.t1;

public class C1 extends Thread{
	int num;

	@Override
	public void run(){
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(num);
	}
}
