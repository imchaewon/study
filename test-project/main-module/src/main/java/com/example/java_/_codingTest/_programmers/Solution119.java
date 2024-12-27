package com.example.java_._codingTest._programmers;

//가운데 글자 가져오기
//단어 s의 가운데 글자를 반환하는 함수, solution을 만들어 보세요. 단어의 길이가 짝수라면 가운데 두글자를 반환하면 됩니다.
public class Solution119 {
	public static void main(String[] args) {
		Solution119 s = new Solution119();
		System.out.println(s.solution("abcde"));
		System.out.println(s.solution("abcd"));
	}

	public String solution(String s) {
		return s.length() % 2 == 0 ? s.substring(s.length() / 2 - 1, s.length() / 2 + 1) : String.valueOf(s.charAt(s.length() / 2));
	}
}