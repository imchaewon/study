package com.example.java_.methodReference.t1;

public class Run {
	public static void main(String[] args) {

		MathInterface i = Math::random;
		System.out.println(i.get()); // 0.6163065003355584 => 호출이 가능하다.

	}
}
