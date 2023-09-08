package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.stream.Collectors;

//A로 B 만들기
//문자열 before와 after가 매개변수로 주어질 때, before의 순서를 바꾸어 after를 만들 수 있으면 1을, 만들 수 없으면 0을 return 하도록 solution 함수를 완성해보세요.
public class Solution68 {
	public static void main(String[] args) {
		Solution68 s = new Solution68();
		System.out.println(s.solution("olleh","hello"));
	}

	public int solution(String before, String after) {
//		return before.chars().sorted().mapToObj(String::valueOf).collect(Collectors.joining()).equals(after.chars().sorted().mapToObj(String::valueOf).collect(Collectors.joining())) ? 1 : 0;
		return Arrays.stream(before.split("")).sorted().collect(Collectors.joining()).equals(Arrays.stream(before.split("")).sorted().collect(Collectors.joining())) ? 1 : 0;
	}

}
