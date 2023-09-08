package com.example.java_.stream.initial.builder.t1;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {
	public static void main(String[] args) {

		System.out.println(Stream.<String>builder()
				.add("aa")
				.add("bb")
				.build()
				.collect(Collectors.toList()));

	}
}
