package com.example.java_._codingTest._programmers;

import java.util.Arrays;
import java.util.stream.Collectors;

//문자열 밀기
//문자열 "hello"에서 각 문자를 오른쪽으로 한 칸씩 밀고 마지막 문자는 맨 앞으로 이동시키면 "ohell"이 됩니다.
//이것을 문자열을 민다고 정의한다면 문자열 A와 B가 매개변수로 주어질 때,
//A를 밀어서 B가 될 수 있다면 몇 번 밀어야 하는지 횟수를 return하고 밀어서 B가 될 수 없으면 -1을 return 하도록 solution 함수를 완성해보세요.
public class Solution90 {
	public static void main(String[] args) {
		Solution90 s = new Solution90();
		System.out.println(s.solution("hello","ohell"));
		System.out.println(s.solution("hello","hello"));
		System.out.println(s.solution2("hello","ohell"));
	}

	public int solution(String A, String B) {
		if(A.equals(B))
			return 0;
		String[] s1 = A.split("");
		for (int i = 0; i < A.length() - 1; i++) {
			String tmp = "";
			for (int j = s1.length - 1; j >= 0; j--) {
				if (j == s1.length - 1)
					tmp = s1[s1.length - 1];
				s1[j] = j == 0 ? tmp : s1[j - 1];
			}
			if (Arrays.stream(s1).collect(Collectors.joining()).equals(B))
				return i + 1;
		}
		return -1;
	}

	public int solution2(String A, String B) {
		String tempB = B.repeat(2);
		return tempB.indexOf(A);
	}
}