package com.example.java_.z.프로그래머스문제;

//특정 문자 제거하기
//문자열 my_string과 문자 letter이 매개변수로 주어집니다. my_string에서 letter를 제거한 문자열을 return하도록 solution 함수를 완성해주세요.
public class Solution28 {
	public static void main(String[] args) {
		Solution28 s = new Solution28();
		System.out.println(s.solution("abcdef","f"));
	}

	public String solution(String my_string, String letter) {
		return my_string.replace(letter,"");
	}
}
