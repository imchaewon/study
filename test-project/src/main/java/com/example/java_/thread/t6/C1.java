package com.example.java_.thread.t6;

public class C1 extends Thread{

	public int num;

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
