package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//숫자 찾기
//정수 num과 k가 매개변수로 주어질 때, num을 이루는 숫자 중에 k가 있으면 num의 그 숫자가 있는 자리 수를 return하고 없으면 -1을 return 하도록 solution 함수를 완성해보세요.
public class Solution61 {
	public static void main(String[] args) {
		Solution61 s = new Solution61();
		System.out.println(s.solution(29183,1));
		System.out.println(s.solution2(29183,1));
		System.out.println(s.solution3(29183,1));
	}

	public int solution(int num, int k) {
		int idx = String.valueOf(num).indexOf(Arrays.stream(String.valueOf(num).split("")).filter(n -> Integer.parseInt(n) == k).findAny().orElse("_"));
		return idx >= 0 ? idx + 1 : -1;
	}
	public int solution2(int num, int k) {
		return String.valueOf(num).contains(String.valueOf(k)) ? String.valueOf(num).indexOf(String.valueOf(k)) + 1 : -1;
	}
	public int solution3(int num, int k) {
		return ("_" + num).indexOf(String.valueOf(k));
	}

}
