package com.example.java_.stream.intermediate.map.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		List<String> list = Arrays.asList(" 가가","나 나","다다 ");

//		List<String> list2 = list.stream()
//				.map(asd -> asd.trim() + "a")
//				.collect(Collectors.toList());
		List<String> list2 = list.stream()
				.map(asd -> asd.trim() + "a")
				.collect(Collectors.toList());


		System.out.println(list);
		System.out.println(list2);

	}
}
