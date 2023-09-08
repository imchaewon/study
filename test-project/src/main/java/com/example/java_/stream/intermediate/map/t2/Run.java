package com.example.java_.stream.intermediate.map.t2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		Collection<SampleDto2> list = Arrays.asList(
					SampleDto2.builder()
						.age(1)
						.gender("남자")
						.build(),
					SampleDto2.builder()
						.age(2)
						.gender("여자")
						.build()
				);

		Collection<SampleDto2> list2 = list.stream()
				.collect(Collectors.toList());

		List<String> list3 = list.stream()
				.map(asd -> asd+"cc")
				.collect(Collectors.toList());


		System.out.println(list);
		System.out.println(list2);
		System.out.println(list3);

	}
}
