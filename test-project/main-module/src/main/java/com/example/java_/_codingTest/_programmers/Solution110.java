package com.example.java_._codingTest._programmers;

//문자열을 정수로 바꾸기
//문자열 s를 숫자로 변환한 결과를 반환하는 함수, solution을 완성하세요.
public class Solution110 {
	public static void main(String[] args) {
		Solution110 s = new Solution110();
		System.out.println(s.solution("123"));
		System.out.println(s.solution("+123"));
		System.out.println(s.solution("-123"));
	}

	public int solution(String s) {
		return Integer.parseInt(s);
	}
}