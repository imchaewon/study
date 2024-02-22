package com.example.java_.stream.intermediate.flatMap.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		List<C1> list = Arrays.asList(
			new C1(1, Arrays.asList(new C2("aa")))
		);

		System.out.println(list);

		List<C2> changeList = list.stream().flatMap(i -> i.getBbbbb().stream()).collect(Collectors.toList());

		System.out.println(changeList);

	}
}
