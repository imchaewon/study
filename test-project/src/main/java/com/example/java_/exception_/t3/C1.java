package com.example.java_.exception_.t3;

public class C1 {

	static void m1() throws Exception {
		throw new Exception("예외");
	}

	public static void main(String[] args) throws Exception {

		m1();
		System.out.println(111);

	}
}
