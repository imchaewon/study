package com.example.java_._codingTest._programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

//중복된 문자 제거
//문자열 my_string이 매개변수로 주어집니다. my_string에서 중복된 문자를 제거하고 하나의 문자만 남긴 문자열을 return하도록 solution 함수를 완성해주세요.
public class Solution65 {
	public static void main(String[] args) {
		Solution65 s = new Solution65();
		System.out.println(s.solution("people"));
	}

	public String solution(String my_string) {
		return Arrays.stream(my_string.split("")).distinct().collect(Collectors.joining());
	}

}