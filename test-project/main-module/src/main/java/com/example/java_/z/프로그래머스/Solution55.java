package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//가장 큰 수 찾기
//정수 배열 array가 매개변수로 주어질 때, 가장 큰 수와 그 수의 인덱스를 담은 배열을 return 하도록 solution 함수를 완성해보세요.
public class Solution55 {
	public static void main(String[] args) {
		Solution55 s = new Solution55();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 8, 3})));
	}

	public int[] solution(int[] array) {
		List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		int max = Collections.max(list);
		return new int[]{max, list.indexOf(max)};
	}

}
