package com.example.java_.consumer.t1;

import java.util.function.Consumer;

public class Run {
	public static void main(String[] args) {
		Consumer<Integer> consumer = p1 -> System.out.println(p1 + " * 10 = " + (p1 * 10));
		consumer.accept(100);
		consumer.accept(200);
		consumer.accept(300);
	}
}
