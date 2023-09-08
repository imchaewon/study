package com.example.java_.annotation.lombok.nonNull.t3;

import lombok.NonNull;

public class Run {
	public static void main(String[] args) {

		System.out.println(m1());

	}

	@NonNull
	private static String m1() {
		return null;
	}
}
