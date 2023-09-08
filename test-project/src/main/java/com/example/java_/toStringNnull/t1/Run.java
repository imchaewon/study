package com.example.java_.toStringNnull.t1;

public class Run {
	public static void main(String[] args) {

		String s1 = "asd";
		String s2 = null;

		System.out.println(s1.toString());
		System.out.println(s1+"");

		System.out.println(s2.toString()); // null값에는 toString을 못씀
		System.out.println(s2+"");

	}
}
