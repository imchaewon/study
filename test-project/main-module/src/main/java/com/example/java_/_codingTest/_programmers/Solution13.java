package com.example.java_._codingTest._programmers;

// 나이 출력
// 머쓱이는 40살인 선생님이 몇 년도에 태어났는지 궁금해졌습니다. 나이 age가 주어질 때, 2022년을 기준 출생 연도를 return 하는 solution 함수를 완성해주세요.
public class Solution13 {
	public static void main(String[] args) {
		Solution13 s = new Solution13();
		s.solution(20);
	}
	public int solution(int age) {
		return 2022 - age + 1;
	}
}