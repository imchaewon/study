package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//중앙값 구하기
//중앙값은 어떤 주어진 값들을 크기의 순서대로 정렬했을 때 가장 중앙에 위치하는 값을 의미합니다.
//예를 들어 1, 2, 7, 10, 11의 중앙값은 7입니다. 정수 배열 array가 매개변수로 주어질 때, 중앙값을 return 하도록 solution 함수를 완성해보세요.
public class Solution39 {
	public static void main(String[] args) {
		Solution39 s = new Solution39();
		System.out.println(s.solution(new int[]{1, 2, 7, 10, 11}));
	}

	public int solution(int[] array) {
		return Arrays.stream(array).sorted().toArray()[array.length / 2];
	}

}
