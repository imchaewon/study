package com.example.java_.exception_.t3;

public class C2 {
	public static void main(String[] args) {

		try {
			System.out.println(111);
			throw new Exception("예외");
//			System.out.println(222);
		} catch (Exception e) {
			System.out.println("C2.main()");
			e.printStackTrace();
		}

		System.out.println(333);

	}
}
