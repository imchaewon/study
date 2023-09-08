package com.example.java_.stream.intermediate.flatMapToInt.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		Run r = new Run();

		List<String> list = new ArrayList<String>();

		list.add("123 888");
		list.add("444 555");
		list.add("777 2323");
		list.add("1399 22");

		System.out.println(list);

		System.out.println(Arrays.toString(r.m1(list)));

	}

	private int[] m1(List<String> list) {
		return list.stream().flatMapToInt(s -> {
			String[] sarr = s.split(" ");
			int[] iarr = Arrays.stream(sarr).mapToInt(Integer::parseInt).toArray();
			return Arrays.stream(iarr);
		}).toArray();
	}

	private int[] m2(List<String> list) {
		return list.stream()
			.flatMapToInt(str -> {
				String[] sArr = str.split(" ");
				int[] arr = new int[sArr.length];
				for (int i = 0; i < sArr.length; i++) {
					arr[i] = Integer.parseInt(sArr[i]);
				}
				return Arrays.stream(arr);
			}).toArray();
	}
}
