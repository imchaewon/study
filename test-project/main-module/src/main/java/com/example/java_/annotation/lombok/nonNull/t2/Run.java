package com.example.java_.annotation.lombok.nonNull.t2;

import lombok.NonNull;

public class Run {
	public static void main(String[] args) {
		m1(null);
	}

	static void m1(@NonNull String s1){
		System.out.println("m1");
	}

}
