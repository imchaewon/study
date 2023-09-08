package com.example.java_.z.프로그래머스문제;

import java.util.stream.Collectors;

//외계행성의 나이
//우주여행을 하던 머쓱이는 엔진 고장으로 PROGRAMMERS-962 행성에 불시착하게 됐습니다.
//입국심사에서 나이를 말해야 하는데, PROGRAMMERS-962 행성에서는 나이를 알파벳으로 말하고 있습니다.
//a는 0, b는 1, c는 2, ..., j는 9입니다. 예를 들어 23살은 cd, 51살은 fb로 표현합니다.
//나이 age가 매개변수로 주어질 때 PROGRAMMER-962식 나이를 return하도록 solution 함수를 완성해주세요.
public class Solution57 {
	public static void main(String[] args) {
		Solution57 s = new Solution57();
		System.out.println(s.solution(23));
		System.out.println(s.solution2(23));
	}

	public String solution(int age) {
		StringBuilder answer = new StringBuilder();

		while(age > 0) {
			char c = (char) (Integer.toString(age % 10).charAt(0) + 49);
			answer.insert(0,String.valueOf(c));
			age /= 10;
		}

		return answer.toString();
	}

	public String solution2(int age) {
		return String.valueOf(age).chars().mapToObj(c -> String.valueOf((char)(c + 49))).collect(Collectors.joining());
	}

}
