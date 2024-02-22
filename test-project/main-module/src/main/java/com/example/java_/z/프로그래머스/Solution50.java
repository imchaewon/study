package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.stream.Collectors;

//대문자와 소문자
//문자열 my_string이 매개변수로 주어질 때, 대문자는 소문자로 소문자는 대문자로 변환한 문자열을 return하도록 solution 함수를 완성해주세요.
public class Solution50 {
	public static void main(String[] args) {
		Solution50 s = new Solution50();
		System.out.println(s.solution("cccCCC"));
	}

	public String solution(String my_string) {
		return Arrays.stream(my_string.split("")).map(s -> s.matches("[a-z]") ? s.toUpperCase() : s.toLowerCase()).collect(Collectors.joining());
	}

}
