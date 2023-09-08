package com.example.java_.z.프로그래머스문제;

// 문자열 내 p와 y의 개수
public class Solution5 {
	boolean solution(String s) {
		boolean answer = true;

		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
		int p1 = 0;
		int p2 = 0;
		int p3 = 0;
		int p4 = 0;
		while (s.toLowerCase().indexOf("p", p1) >= 0) {
			p2++;
			p1 = s.toLowerCase().indexOf("p",p1) + 1;
		}
		while (s.toLowerCase().indexOf("y", p3) >= 0) {
			p4++;
			p3 = s.toLowerCase().indexOf("y",p3) + 1;
		}

		if (p2 != p4) {
			answer = false;
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution5 s = new Solution5();
		System.out.println(s.solution("pPoooyY"));
	}
}
