package com.example.java_.thread.t2;

public class C1 extends Thread{
	@Override
	public void run(){
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
}
