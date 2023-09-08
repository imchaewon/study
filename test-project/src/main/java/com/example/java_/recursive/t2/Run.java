package com.example.java_.recursive.t2;

public class Run {
	public static void main(String[] args) {

		System.out.println(m1(10));

	}

	private static int m1(int i) {
		if(i == 1 )
			return i;
		return m1(--i);
	}
}

