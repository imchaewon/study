package com.example.java_.ramda.t5;

// test4를 람다로 구현
public class Sample {
	public static void main(String[] args) {

		Calculator c = (int a, int b) -> a + b;

		int result = c.compare(1, 2);

		System.out.println(result);

	}
}
