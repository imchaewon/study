package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//이상한 문자 만들기
//문자열 s는 한 개 이상의 단어로 구성되어 있습니다. 각 단어는 하나 이상의 공백문자로 구분되어 있습니다.
//각 단어의 짝수번째 알파벳은 대문자로, 홀수번째 알파벳은 소문자로 바꾼 문자열을 리턴하는 함수, solution을 완성하세요.
//문자열 전체의 짝/홀수 인덱스가 아니라, 단어(공백을 기준)별로 짝/홀수 인덱스를 판단해야합니다.
//첫 번째 글자는 0번째 인덱스로 보아 짝수번째 알파벳으로 처리해야 합니다.
public class Solution130 {
	public static void main(String[] args) {
		Solution130 s = new Solution130();
//		System.out.println(s.solution("try hello world"));
//		System.out.println(s.solution("abc abcd abcde")); // 기댓값 〉"AbC AbCd AbCdE"
		System.out.println(s.solution(" hello")); // 기댓값 〉" HeLlO"
		System.out.println(s.solution("hello "));
		System.out.println(s.solution("   A   a    AaAaAaAaA     aAaAaAa     ")); // 기댓값 >"   A   A    AaAaAaAaA     AaAaAaA     "
	}

	public String solution(String s) {
		int num = 0;
		while(s.charAt(s.length() - (num + 1)) == 32)
			num++;
		StringBuilder answer = new StringBuilder();
		String[] a1 = s.split(" ");
		System.out.println(Arrays.toString(a1));
		for (int i=0;i<a1.length;i++) {
			String[] a2 = a1[i].split("");
			for (int j = 0; j < a2.length; j++)
				answer.append(j % 2 == 0 ? a2[j].toUpperCase() : a2[j].toLowerCase());
			if(i == a1.length - 1)
				break;
			answer.append(" ");
		}
		return answer + " ".repeat(num);
	}
}



