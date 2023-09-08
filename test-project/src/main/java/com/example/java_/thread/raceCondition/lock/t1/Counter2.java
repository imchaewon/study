package com.example.java_.thread.raceCondition.lock.t1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter2 {
	private int count;

	public Counter2(){
		count = 0;
	}

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
