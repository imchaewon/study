package com.example.java_.optional.t6;

import java.util.Optional;

public class Run {
	public static void main(String[] args) {
		Optional<Integer> o1 = Optional.ofNullable(123);

		o1.ifPresent(e -> {
			System.out.println(e);
		});
	}
}
