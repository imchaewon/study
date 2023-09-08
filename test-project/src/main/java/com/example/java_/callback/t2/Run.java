package com.example.java_.callback.t2;

public class Run {
	public static void main(String[] args) {

		Callback.m1("asd",param1 -> {
			System.out.println("callback start....");
			System.out.println(param1);
		});

	}
}
