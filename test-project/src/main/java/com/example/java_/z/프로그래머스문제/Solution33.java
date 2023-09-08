package com.example.java_.z.프로그래머스문제;

//편지
//머쓱이는 할머니께 생신 축하 편지를 쓰려고 합니다.
//할머니가 보시기 편하도록 글자 한 자 한 자를 가로 2cm 크기로 적으려고 하며,
//편지를 가로로만 적을 때, 축하 문구 message를 적기 위해 필요한 편지지의 최소 가로길이를 return 하도록 solution 함수를 완성해주세요.
public class Solution33 {
	public static void main(String[] args) {
		Solution33 s = new Solution33();
		System.out.println(s.solution("happy birthday!"));
	}

	public int solution(String message) {
		return message.length() * 2;
	}
}
