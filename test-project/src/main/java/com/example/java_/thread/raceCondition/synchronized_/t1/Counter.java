package com.example.java_.thread.raceCondition.synchronized_.t1;

public class Counter {
	private int count;

	public void increment(){
		synchronized (this){
			count++;
		}
	}

	public void decrement(){
		synchronized (this){
			count--;
		}
	}

	public int getCount(){
		synchronized (this){
			return count;
		}
	}

}
