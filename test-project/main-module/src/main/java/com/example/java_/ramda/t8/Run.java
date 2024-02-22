package com.example.java_.ramda.t8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Run {
	public static void main(String[] args) {

		List<String> list = Arrays.asList("가","나","다");

		int i = 0;
		for (String s : list) {
			i++;
		}
		System.out.println(i);

		AtomicInteger i2 = new AtomicInteger();
		list.forEach(item -> {
			i2.getAndIncrement();
		});
		System.out.println(i2.get());

		final int[] i3 = {0};
		list.forEach(item -> {
			i3[0]++;
		});
		System.out.println(i3[0]);

	}
}
