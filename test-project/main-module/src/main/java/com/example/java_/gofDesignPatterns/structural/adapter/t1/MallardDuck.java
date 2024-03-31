package com.example.java_.gofDesignPatterns.structural.adapter.t1;

public class MallardDuck implements Duck{
	@Override
	public void quack() {
		System.out.println("꽉꽉");
	}

	@Override
	public void fly() {
		System.out.println("오리날다");
	}
}
