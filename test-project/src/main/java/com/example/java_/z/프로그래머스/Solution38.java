package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//배열의 유사도
//두 배열이 얼마나 유사한지 확인해보려고 합니다. 문자열 배열 s1과 s2가 주어질 때 같은 원소의 개수를 return하도록 solution 함수를 완성해주세요.
public class Solution38 {
	public static void main(String[] args) {
		Solution38 s = new Solution38();
		System.out.println(s.solution(new String[]{"a", "b", "c"}, new String[]{"com", "b", "d", "p", "c"}));
		System.out.println(s.solution2(new String[]{"a", "b", "c"}, new String[]{"com", "b", "d", "p", "c"}));
	}

	public int solution(String[] s1, String[] s2) {
		int answer = 0;
		for(String i1 : s1)
			for (String i2 : s2)
				if(i1.equals(i2)){
					answer++;
					break;
				}
		return answer;
	}

	public int solution2(String[] s1, String[] s2) {
		return Arrays.stream(s1).filter(s -> Arrays.asList(s2).contains(s)).toArray(String[]::new).length;
	}
}
