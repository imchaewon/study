package com.example.java_.consumer.t2;

import java.util.function.Consumer;

public class Run {
	public static void main(String[] args) {
		Consumer<Integer> consumerAdd = p1 -> System.out.println(p1 + " + 100 = " + (p1 + 100));
		Consumer<Integer> consumerMinus = p1 -> System.out.println(p1 + " - 100 = " + (p1 - 100));
		Consumer<Integer> consumerMultiple = p1 -> System.out.println(p1 + " * 100 = " + (p1 * 100));

		consumerAdd.andThen(consumerMinus.andThen(consumerMultiple)).accept(50);
	}
}
