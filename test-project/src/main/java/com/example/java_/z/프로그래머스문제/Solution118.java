package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.stream.IntStream;

//없는 숫자 더하기
//0부터 9까지의 숫자 중 일부가 들어있는 정수 배열 numbers가 매개변수로 주어집니다.
//numbers에서 찾을 수 없는 0부터 9까지의 숫자를 모두 찾아 더한 수를 return 하도록 solution 함수를 완성해주세요.
public class Solution118 {
	public static void main(String[] args) {
		Solution118 s = new Solution118();
		System.out.println(s.solution(new int[]{1,2,3,4,6,7,8,0}));
	}

	public int solution(int[] numbers) {
		return IntStream.range(0, 10).filter(n -> Arrays.stream(numbers).noneMatch(n2 -> n == n2)).sum();
	}
}



