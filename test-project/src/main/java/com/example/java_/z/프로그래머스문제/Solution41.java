package com.example.java_.z.프로그래머스문제;

//문자열안에 문자열
//문자열 str1, str2가 매개변수로 주어집니다. str1 안에 str2가 있다면 1을 없다면 2를 return하도록 solution 함수를 완성해주세요.
public class Solution41 {
	public static void main(String[] args) {
		Solution41 s = new Solution41();
		System.out.println(s.solution("ab6CDE443fgh22iJKlmn1o","6CD"));
	}

	public int solution(String str1, String str2) {
		return str1.contains(str2) ? 1 : 2;
	}

}
