package com.example.java_.ramda.t3_2;

public class MathBox {
	private int instance_value;

	public MathBox() {}

	public MathBox(int value) {
		this.instance_value = value;
	}

	// 더하기
	public int sum(int a, int b) {
		return a + b;
	}

	// 빼기
	public int sub(int a, int b) {
		return a - b;
	}

	// 나누기
	public static int div(int a, int b) {
		return a / b;
	}

	//곱하기
	public static int mul (int a, int b) {
		return a * b;
	}

	public int getSumWithInstance(int a, int b) {
		return this.instance_value + a + b;
	}
}
