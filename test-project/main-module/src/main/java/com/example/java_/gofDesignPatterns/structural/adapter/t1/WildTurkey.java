package com.example.java_.gofDesignPatterns.structural.adapter.t1;

public class WildTurkey implements Turkey{
	@Override
	public void gobble() {
		System.out.println("고블고블");
	}

	@Override
	public void fly() {
		System.out.println("들칠면조날다");
	}
}
