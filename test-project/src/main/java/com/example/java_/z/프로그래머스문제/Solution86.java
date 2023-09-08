package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//삼각형의 완성조건 (2)
//선분 세 개로 삼각형을 만들기 위해서는 다음과 같은 조건을 만족해야 합니다.
//﹒가장 긴 변의 길이는 다른 두 변의 길이의 합보다 작아야 합니다.
//삼각형의 두 변의 길이가 담긴 배열 sides이 매개변수로 주어집니다. 나머지 한 변이 될 수 있는 정수의 개수를 return하도록 solution 함수를 완성해주세요.
public class Solution86 {
	public static void main(String[] args) {
		Solution86 s = new Solution86();
		System.out.println(s.solution(new int[]{1, 2}));
		System.out.println(s.solution(new int[]{3, 6}));
		System.out.println(s.solution(new int[]{11, 7}));
	}

	public int solution(int[] sides) {
		// 큰 - 작 + 1 ~ 큰
		// +
		// 큰 + 1 ~ 큰 + 작 - 1
		// 중복 제거

		int[] arr = Arrays.stream(sides).sorted().toArray();

		Set<Integer> s1 = IntStream.rangeClosed(arr[1] - arr[0] + 1, arr[1]).boxed().collect(Collectors.toSet());
		Set<Integer> s2 = IntStream.rangeClosed(arr[1] + 1, arr[1] + arr[0] - 1).boxed().collect(Collectors.toSet());

		s1.addAll(s2);
		return s1.size();
	}

}










