package com.example.java_.ramda.t3_2;

public class Number {
	private int a;
	private int b;

	public Number(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public void print(Function func) {
		System.out.println(func.func(this.a, this.b));
	}

	public int sum() {
		return this.a + this.b;
	}



}
