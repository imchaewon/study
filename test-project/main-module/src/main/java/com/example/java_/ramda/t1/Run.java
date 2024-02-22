package com.example.java_.ramda.t1;

public class Run {
	// 람다식 문법 (매개변수 목록) -> {실행문}
	public static void exec(Compare com) {
		int i = 10;
		int j = 20;
		int value = com.compareTo(i,j);
		System.out.println(value);
	}

	public static void main(String[] args) {
		exec((a,b) -> {
			return a + b;
		});
	}
}
