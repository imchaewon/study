package com.example.java_.thread.raceCondition.synchronized_.t2;

public class Counter2 {
	private int count;

	public void increment(){
		count++;
	}

	public void decrement(){
		count--;
	}

	public int getCount(){
		return count;
	}

}
