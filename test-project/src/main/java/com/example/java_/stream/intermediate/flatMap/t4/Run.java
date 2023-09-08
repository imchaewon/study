package com.example.java_.stream.intermediate.flatMap.t4;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int[][] arr = {{1, 2}, {3, 4}};

		System.out.println(Arrays.toString(Arrays.stream(arr).flatMap(a -> Arrays.stream(a).mapToObj(e -> e + "a")).toArray()));

	}
}
