package com.example.java_.assert_;

public class T1 {
	public static void main(String[] args) {
		m1();
	}

	public static void m1(){
		String s1 = null;

		int rand = (int) Math.floor(Math.random() * 2);

		System.out.println(rand);

		if (rand == 1){
			s1 = "asd";
		}

		System.out.println(s1);

		assert s1 != null;

		System.out.println("끝");

	}
}
