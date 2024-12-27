package com.example.java_._codingTest._programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

//7의 개수
//머쓱이는 행운의 숫자 7을 가장 좋아합니다. 정수 배열 array가 매개변수로 주어질 때, 7이 총 몇 개 있는지 return 하도록 solution 함수를 완성해보세요.
public class Solution2 {
	public static void main(String[] args) {
		Solution2 s = new Solution2();
		System.out.println(s.solution(new int[]{7, 77, 17}));
	}

	public int solution(int[] array) {
		return (int) Arrays.stream(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining()).split("")).filter(s -> s.equals("7")).count();
	}

}