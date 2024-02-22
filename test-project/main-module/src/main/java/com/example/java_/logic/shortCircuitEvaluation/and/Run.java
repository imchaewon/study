package com.example.java_.logic.shortCircuitEvaluation.and;

public class Run {
	public static void main(String[] args) {

		int a = 0;
		int b = 0;

		if(a++ > 10 && b++ > 10);

		System.out.printf("%d %d\r\n", a, b);

		a = 0;

		if(a++ > 10 & b++ > 10);

		System.out.printf("%d %d\r\n", a, b);

	}
}
