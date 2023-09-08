package com.example.java_.z.프로그래머스문제;

import java.util.stream.IntStream;

//제곱수 판별하기
//어떤 자연수를 제곱했을 때 나오는 정수를 제곱수라고 합니다. 정수 n이 매개변수로 주어질 때, n이 제곱수라면 1을 아니라면 2를 return하도록 solution 함수를 완성해주세요.
public class Solution42 {
	public static void main(String[] args) {
		Solution42 s = new Solution42();
		System.out.println(s.solution(144));
		System.out.println(s.solution2(144));
	}

	public int solution(int n) {
		return IntStream.rangeClosed(1, n / 3).filter(i -> i * i == n).findAny().isPresent() ? 1 : 2;
	}

	public int solution2(int n) {
		return n % Math.sqrt(n) == 0 ? 1 : 2;
	}

}
