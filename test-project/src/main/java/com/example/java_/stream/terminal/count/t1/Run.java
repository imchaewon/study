package com.example.java_.stream.terminal.count.t1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		System.out.println(IntStream.of(1,2,3,4,5).filter(n -> n < 3).count());

		System.out.println(Arrays.stream(new int[][]{{1,2,3},{2,3,4}}).count());

		System.out.println();

		Set<Integer> set = new HashSet<>();

		System.out.println(Arrays.toString(Arrays.stream(new int[][]{{1, 3}, {2, 5}}).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).toArray()));
		System.out.println(Arrays.toString(Arrays.stream(new int[][]{{1, 3}, {2, 5}}).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).filter(n -> !set.add(n)).toArray()));
//		System.out.println(Arrays.stream(new int[][]{{1, 3}, {2, 5}}).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).filter(n -> !set.add(n)).count()); // 같은set을 쓰기때문에 실행시 위에거 지워야함
//		System.out.println(Arrays.toString(Arrays.stream(new int[][]{{1, 3}, {2, 5}}).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).map(n -> set.add(n) ? 0 : 1).toArray())); // 같은set을 쓰기때문에 실행시 위에거 지워야함


	}
}
