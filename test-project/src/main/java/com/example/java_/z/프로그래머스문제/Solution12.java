package com.example.java_.z.프로그래머스문제;

// 숫자 비교하기
// 정수 num1과 num2가 매개변수로 주어집니다. 두 수가 같으면 1 다르면 -1을 retrun하도록 solution 함수를 완성해주세요.
public class Solution12 {
	public static void main(String[] args) {
		Solution12 s = new Solution12();
		s.solution(1,2);
	}
	public int solution(int num1, int num2) {
		return num1 == num2 ? 1 : -1;
	}
}
