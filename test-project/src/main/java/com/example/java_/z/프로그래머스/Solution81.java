package com.example.java_.z.프로그래머스;

//종이 자르기
//머쓱이는 큰 종이를 1 x 1 크기로 자르려고 합니다. 예를 들어 2 x 2 크기의 종이를 1 x 1 크기로 자르려면 최소 가위질 세 번이 필요합니다.
public class Solution81 {
	public static void main(String[] args) {
		Solution81 s = new Solution81();
		System.out.println(s.solution(2, 2));
	}

	public int solution(int M, int N) {
		return M * N - 1;
	}

}
