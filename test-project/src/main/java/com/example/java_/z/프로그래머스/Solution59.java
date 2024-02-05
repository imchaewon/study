package com.example.java_.z.프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//최댓값 만들기 (2)
//정수 배열 numbers가 매개변수로 주어집니다. numbers의 원소 중 두 개를 곱해 만들 수 있는 최댓값을 return하도록 solution 함수를 완성해주세요.
public class Solution59 {
	public static void main(String[] args) {
		Solution59 s = new Solution59();
		System.out.println(s.solution(new int[]{1, 2, -3, 4, -100, 5, -100}));
		System.out.println(s.solution2(new int[]{1, 2, -3, 4, -100, 5, -100}));
	}

	public int solution(int[] numbers) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < numbers.length; i++) {
			int finalI = i;
			AtomicInteger idx = new AtomicInteger();
			list.add(Arrays.stream(numbers).filter(n -> idx.getAndIncrement() != finalI).map(n -> n * numbers[finalI]).max().orElse(0));
		}

		return Collections.max(list);
	}

	public int solution2(int[] numbers) {
		Arrays.sort(numbers);
		return Math.max(numbers[0] * numbers[1], numbers[numbers.length - 1] * numbers[numbers.length - 2]);
	}

//	public int solution2(int[] numbers) {
//		return Arrays.stream(numbers).map(n -> Arrays.stream(numbers).filter(i -> i != n).map(i -> i * n).max().orElse(0)).max().orElse(0);
//	}

}
