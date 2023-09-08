package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.stream.IntStream;

//x만큼 간격이 있는 n개의 숫자
//함수 solution은 정수 x와 자연수 n을 입력 받아, x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트를 리턴해야 합니다. 다음 제한 조건을 보고, 조건을 만족하는 함수, solution을 완성해주세요.
public class Solution109 {
	public static void main(String[] args) {
		Solution109 s = new Solution109();
		System.out.println(Arrays.toString(s.solution(2, 5)));
		System.out.println(Arrays.toString(s.solution(4, 3)));
		System.out.println(Arrays.toString(s.solution(-4, 2)));
	}

	public long[] solution(int x, int n) {
		return IntStream.rangeClosed(1, n).mapToLong(n2 -> (long) n2 * x).toArray();
	}
}



