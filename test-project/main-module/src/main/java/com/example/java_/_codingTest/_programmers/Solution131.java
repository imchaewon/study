package com.example.java_._codingTest._programmers;

//3진법 뒤집기
//자연수 n이 매개변수로 주어집니다. n을 3진법 상에서 앞뒤로 뒤집은 후, 이를 다시 10진법으로 표현한 수를 return 하도록 solution 함수를 완성해주세요.
public class Solution131 {
	public static void main(String[] args) {
		Solution131 s = new Solution131();
		System.out.println(s.solution(45));
	}

	public int solution(int n) {
		return Integer.parseInt(String.valueOf(new StringBuilder().append(Integer.toString(n, 3)).reverse()), 3);
	}
}