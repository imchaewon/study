package com.example.java_.function_.t2;

import java.util.function.Function;

public class Run {
	public static void main(String[] args) {

		Function<String, String> func1 = Run::m1;

		System.out.println(func1.apply("zzzzzzz"));


	}

	public static String m1(String s){
		return s + "aaaa";
	}
}
