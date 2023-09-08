package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//삼각형의 완성조건 (1)
//선분 세 개로 삼각형을 만들기 위해서는 다음과 같은 조건을 만족해야 합니다.
//가장 긴 변의 길이는 다른 두 변의 길이의 합보다 작아야 합니다.
//삼각형의 세 변의 길이가 담긴 배열 sides이 매개변수로 주어집니다. 세 변으로 삼각형을 만들 수 있다면 1, 만들 수 없다면 2를 return하도록 solution 함수를 완성해주세요.
public class Solution30 {
	public static void main(String[] args) {
		Solution30 s = new Solution30();
		System.out.println(s.solution(new int[]{1, 2, 3}));
	}

	public int solution(int[] sides) {
		int max = Arrays.stream(sides).max().orElse(0);
		return Arrays.stream(sides).sum() - max > max ? 1 : 2;
	}
}
