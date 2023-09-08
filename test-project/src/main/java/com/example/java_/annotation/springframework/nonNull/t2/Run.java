package com.example.java_.annotation.springframework.nonNull.t2;

import org.springframework.lang.NonNull;

public class Run {
	public static void main(String[] args) {
		m1(null);
	}

	static void m1(@NonNull String s1){
		System.out.println("m1");
	}

}
