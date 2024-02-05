package com.example.java_.z.프로그래머스;

import java.util.Arrays;

// 배열 원소의 길이
// 문자열 배열 strlist가 매개변수로 주어집니다. strlist 각 원소의 길이를 담은 배열을 retrun하도록 solution 함수를 완성해주세요.
public class Solution26 {
	public static void main(String[] args) {
		Solution26 s = new Solution26();
		System.out.println(Arrays.toString(s.solution(new String[]{"We", "are", "the", "world!"})));
	}

	public int[] solution(String[] strlist) {
		return Arrays.stream(strlist).mapToInt(String::length).toArray();
	}
}