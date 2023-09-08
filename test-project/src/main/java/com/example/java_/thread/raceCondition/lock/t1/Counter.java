package com.example.java_.thread.raceCondition.lock.t1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	private int count;
	private Lock lock;

	public Counter(){
		count = 0;
		lock = new ReentrantLock();
	}

	public void increment(){
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}

	public void decrement(){
		lock.lock();
		try {
			count--;
		} finally {
			lock.unlock();
		}
	}

	public int getCount(){
		return count;
	}

}
