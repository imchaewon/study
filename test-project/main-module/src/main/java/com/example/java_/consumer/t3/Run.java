package com.example.java_.consumer.t3;

import java.util.function.Consumer;

public class Run {
	public static void main(String[] args) {
		Consumer<Integer> integerConsumer = m1();
//		integerConsumer.accept(123); 함수를 사용하지 않아도 m1 메서드는 호출(실행)이 된 상태
	}

	public static Consumer<Integer> m1(){
		System.out.println("m1()...");
		return p1 -> {
			System.out.println(p1 + 1);
		};
	}
}
