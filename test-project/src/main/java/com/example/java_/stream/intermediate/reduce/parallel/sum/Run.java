package com.example.java_.stream.intermediate.reduce.parallel.sum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

public class Run {
	public static void main(String[] args) {

//		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

//		int[] arr = new int[10000000];
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 100000000; i++) {
//			arr[i] = i + 1;
			list.add(i);
		}

//		IntStream s1 = Arrays.stream(arr);
//		IntStream s2 = Arrays.stream(arr);
		Stream<Integer> s1 = list.stream();
		Stream<Integer> s2 = list.stream();

		Long l1 = Calendar.getInstance().getTimeInMillis();
		s1.reduce((i,j)->i+j).ifPresent(System.out::println);
		Long l2 = Calendar.getInstance().getTimeInMillis();
		s2.parallel().reduce((i,j)->i+j).ifPresent(System.out::println);
		Long l3 = Calendar.getInstance().getTimeInMillis();

		System.out.println(l2 - l1);
		System.out.println(l3 - l2);


	}
}
