package com.example.java_.optional.t5;

import java.util.Optional;

public class Run {
	public static void main(String[] args) {

		Optional<Integer> o1 = Optional.empty();
		Optional<Integer> o2 = Optional.of(11);

		System.out.println(o1.orElse(123));

		if (o1.isPresent()){
			System.out.println("널아님");
		}else{
			System.out.println("널");
		}

		o2.ifPresent(System.out::println);

	}
}
