package com.example.java_._codingTest._programmers;

// 각도기
// 각에서 0도 초과 90도 미만은 예각, 90도는 직각, 90도 초과 180도 미만은 둔각 180도는 평각으로 분류합니다.
// 각 angle이 매개변수로 주어질 때 예각일 때 1, 직각일 때 2, 둔각일 때 3, 평각일 때 4를 return하도록 solution 함수를 완성해주세요.
public class Solution15 {
	public static void main(String[] args) {
		Solution15 s = new Solution15();
		System.out.println(s.solution(70));
		System.out.println(s.solution(91));
		System.out.println(s.solution(180));
	}
	public int solution(int angle) {
		return angle < 90 ? 1 : angle == 90 ? 2 : angle < 180 ? 3 : angle == 180 ? 4 : 0;
	}
}