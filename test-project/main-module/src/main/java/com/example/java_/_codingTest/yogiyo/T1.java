package com.example.java_._codingTest.yogiyo;


public class T1 {
	public static void main(String[] args) {
		System.out.println(new T1().solution("123-123-123"));
		System.out.println(new T1().solution("123 123 123"));
		System.out.println(new T1().solution("123-123-1234"));
		System.out.println(new T1().solution("001-501-511"));
		System.out.println(new T1().solution("123-abc-123"));
	}

	public boolean solution(String S) {
		return S.matches("\\d{3}-\\d{3}-\\d{3}");
	}
}