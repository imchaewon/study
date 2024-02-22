package com.example.java_.z.프로그래머스;

//문자 반복 출력하기
//문자열 my_string과 정수 n이 매개변수로 주어질 때, my_string에 들어있는 각 문자를 n만큼 반복한 문자열을 return 하도록 solution 함수를 완성해보세요.
public class Solution34 {
	public static void main(String[] args) {
		Solution34 s = new Solution34();
		System.out.println(s.solution("hello",3));
		System.out.println(s.solution2("hello",3));
	}

	public String solution(String my_string, int n) {
		String answer = "";
		for (int i = 0; i < my_string.length(); i++) {
			for (int j = 0; j < n; j++) {
				answer += my_string.charAt(i);
			}
		}
		return answer;
	}
	public String solution2(String my_string, int n) {
		String answer = "";
		for (char c : my_string.toCharArray()) {
			for (int i = 0; i < n; i++) {
				answer += c;
			}
		}
		return answer;
	}
}
