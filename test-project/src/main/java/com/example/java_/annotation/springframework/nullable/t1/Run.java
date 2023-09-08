package com.example.java_.annotation.springframework.nullable.t1;

import org.springframework.lang.Nullable;

public class Run {

	@Nullable
	static String name = null;

	public static void main(String[] args) {

		int random = (int)(Math.random() * 2);
		String s1;
		if(random == 1){
			s1 = null;
		}else{
			s1 = "asd";
		}
		m1(s1);

		System.out.println(name);

		System.out.println(m2());

	}

	private static void m1(@Nullable String s1) {
		System.out.println(s1);
	}

	@Nullable
	private static String m2(){
		return null;
	}

}
