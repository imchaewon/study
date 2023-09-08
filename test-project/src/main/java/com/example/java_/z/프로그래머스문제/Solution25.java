package com.example.java_.z.프로그래머스문제;

// 문자열 뒤집기
// 문자열 my_string이 매개변수로 주어집니다. my_string을 거꾸로 뒤집은 문자열을 return하도록 solution 함수를 완성해주세요.
public class Solution25 {
	public static void main(String[] args) {
		Solution25 s = new Solution25();
		System.out.println(s.solution("jaron"));
		System.out.println(s.solution2("jaron"));
	}

	public String solution(String my_string) {
		String answer = "";
		for (int i = my_string.length() - 1; i >= 0; i--)
			answer += my_string.charAt(i);
		return answer;
	}

	public String solution2(String my_string) {
		StringBuilder sb = new StringBuilder();
		sb.append(my_string);
		sb.reverse();
		return sb.toString();
	}
}