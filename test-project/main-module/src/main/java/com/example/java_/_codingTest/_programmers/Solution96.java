package com.example.java_._codingTest._programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//최빈값 구하기
//최빈값은 주어진 값 중에서 가장 자주 나오는 값을 의미합니다.
//정수 배열 array가 매개변수로 주어질 때, 최빈값을 return 하도록 solution 함수를 완성해보세요.
//최빈값이 여러 개면 -1을 return 합니다.
public class Solution96 {
	public static void main(String[] args) {
		Solution96 s = new Solution96();
		System.out.println(s.solution(new int[]{5, 5, 1, 2, 3}));
		System.out.println(s.solution(new int[]{1, 4, 2, 3, 3, 3, 4, 5, 5, 5}));
		System.out.println(s.solution(new int[]{1}));
	}

	public int solution(int[] array) {
		int[][] arrs = Arrays.stream(array).distinct().mapToObj(n -> new int[]{n, (int) Arrays.stream(array).filter(n2 -> n2 == n).count()}).sorted((t0, t1) -> Integer.compare(t1[1], t0[1])).toArray(int[][]::new);
		return arrs.length > 1 ? arrs[0][1] == arrs[1][1] ? -1 : arrs[0][0] : arrs[0][0];
	}

	public int solution2(int[] array) {
		List<Map.Entry<Integer, List<Integer>>> list = new ArrayList<>(Arrays.stream(array).boxed().collect(Collectors.groupingBy(o -> o)).entrySet()).stream().sorted((t0, t1) -> Integer.compare(t1.getValue().size(), t0.getValue().size())).collect(Collectors.toList());
		return list.size() > 1 && list.get(0).getValue().size() - list.get(1).getValue().size() == 0 ? -1 : list.get(0).getKey();
	}

}