package com.example.java_.thread.t8;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<Integer> list = List.of(1,2,3,4,5);
		list.parallelStream().forEach(e -> {
			System.out.println(e);
		});
		System.out.println();
		list.stream().parallel().forEach(e -> {
			System.out.println(e);
		});
	}
}
