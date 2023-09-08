package com.example.java_.z.프로그래머스문제;

//수박수박수박수박수박수?
//길이가 n이고, "수박수박수박수...."와 같은 패턴을 유지하는 문자열을 리턴하는 함수, solution을 완성하세요.
//예를들어 n이 4이면 "수박수박"을 리턴하고 3이라면 "수박수"를 리턴하면 됩니다.
public class Solution120 {
	public static void main(String[] args) {
		Solution120 s = new Solution120();
		System.out.println(s.solution(3));
		System.out.println(s.solution(4));
	}

	public String solution(int n) {
		return "수박".repeat(n / 2 + 1).substring(0, n);
	}
}



