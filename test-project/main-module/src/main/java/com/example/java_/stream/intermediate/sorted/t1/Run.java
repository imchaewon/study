package com.example.java_.stream.intermediate.sorted.t1;

import java.util.Arrays;
import java.util.Comparator;

public class Run {
	public static void main(String[] args) {

		int[] iArr = {2,4,1,3};
		String[] sArr = {"bx","ca","ag"};
		int[][] iArr2d = {{1,2}, {2,4}, {3,1}, {4,3}};
		String[][] sArr2d = {{"q","b"}, {"w","c"}, {"e","a"}};

		System.out.println(Arrays.toString(iArr));
		System.out.println(Arrays.toString(sArr));

		System.out.println();

		System.out.println(Arrays.toString(Arrays.stream(iArr).sorted().toArray()));
		System.out.println(Arrays.toString(Arrays.stream(sArr).sorted().toArray()));

		System.out.println();

		System.out.println(Arrays.deepToString(Arrays.stream(iArr2d).sorted((o1, o2) -> Integer.compare(o1[1], o2[1])).toArray()));
		System.out.println(Arrays.deepToString(Arrays.stream(iArr2d).sorted((o1, o2) -> o1[1] - o2[1]).toArray()));
		System.out.println(Arrays.deepToString(Arrays.stream(iArr2d).sorted(Comparator.comparingInt(o -> o[1])).toArray()));

		System.out.println();

		System.out.println(Arrays.deepToString(Arrays.stream(iArr2d).sorted((o1, o2) -> Integer.compare(o2[1], o1[1])).toArray()));
		System.out.println(Arrays.deepToString(Arrays.stream(iArr2d).sorted((o1, o2) -> o2[1] - o1[1]).toArray()));

		System.out.println();

		System.out.println(Arrays.deepToString(Arrays.stream(sArr2d).sorted((o1, o2) -> o1[1].compareTo(o2[1])).toArray()));
		System.out.println(Arrays.deepToString(Arrays.stream(sArr2d).sorted(Comparator.comparing(o -> o[1])).toArray()));

		System.out.println();

		System.out.println(Arrays.deepToString(Arrays.stream(sArr2d).sorted((o1, o2) -> o2[1].compareTo(o1[1])).toArray()));

	}
}
