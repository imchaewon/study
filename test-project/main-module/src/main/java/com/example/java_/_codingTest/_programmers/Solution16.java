package com.example.java_._codingTest._programmers;

// 두 수의 나눗셈
// 정수 num1과 num2가 매개변수로 주어질 때, num1을 num2로 나눈 값에 1,000을 곱한 후 정수 부분을 return 하도록 soltuion 함수를 완성해주세요.
public class Solution16 {
	public static void main(String[] args) {
		Solution16 s = new Solution16();
		System.out.println(s.solution(3,2));
	}
	public int solution(int num1, int num2) {
		return (int)(Double.parseDouble(String.valueOf(num1)) / Double.parseDouble(String.valueOf(num2)) * 1000);
	}
}