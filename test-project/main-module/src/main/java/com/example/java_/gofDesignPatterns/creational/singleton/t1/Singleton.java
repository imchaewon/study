package com.example.java_.gofDesignPatterns.creational.singleton.t1;

public class Singleton {

	private static Singleton instance = null;
	private int count = 0;

	static public Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
			return instance;
		}
		return instance;
	}

	public void count(){
		count++;
	}

	public int getCount(){
		return count;
	}

}
