package com.example.java_.z.프로그래머스문제;

import java.util.stream.IntStream;

//팩토리얼
//i팩토리얼 (i!)은 1부터 i까지 정수의 곱을 의미합니다.
//예를들어 5! = 5 * 4 * 3 * 2 * 1 = 120 입니다. 정수 n이 주어질 때 다음 조건을 만족하는 가장 큰 정수 i를 return 하도록 solution 함수를 완성해주세요.
public class Solution67 {
	public static void main(String[] args) {
		Solution67 s = new Solution67();
		s.solution(3628800);
		s.solution2(3628800);
	}

	public int solution(int n) {
		long start = System.currentTimeMillis();
		int i = 1;
		while (IntStream.rangeClosed(1, i).reduce((x, y) -> x * y).orElse(0) <= n)
			i++;
		System.out.println(System.currentTimeMillis() - start);
		return i - 1;
	}

	public int solution2(int n) {
		long start = System.currentTimeMillis();
		int fac = 1;
		int i = 0;

		while(true){
			if(fac <= n){
				fac *= i + 1;
				i++;
			}else break;
		}
		System.out.println(System.currentTimeMillis() - start);
		return i-1;
	}

}
