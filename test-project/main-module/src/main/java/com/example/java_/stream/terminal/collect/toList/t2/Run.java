package com.example.java_.stream.terminal.collect.toList.t2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		list.add("가");
		list.add("나나");
		list.add("다다다");
		list.add("라");

		Stream streams = Stream.of("가","나","다");
		Stream streams2 = list.stream();

//		System.out.println(streams.map(s->s.toString().length()).collect(Collectors.toList()));
		System.out.println(streams2.map(s->s.toString().length()).collect(Collectors.toList()));

		Object sss = streams.map(s -> {
					String s1 = s.getClass().getName();
					System.out.println(s1);
					String s2 = s+"zxc";
					System.out.println(s2);
					return s2;
			})
			.peek(System.out::println)
			.collect(Collectors.toList());
		System.out.println(sss);




	}
}
