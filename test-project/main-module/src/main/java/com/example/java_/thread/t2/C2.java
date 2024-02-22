package com.example.java_.thread.t2;

public class C2 extends Thread{
	@Override
	public void run(){
		for (int i = 10; i < 20; i++) {
			System.out.println(i);
		}
	}
}
