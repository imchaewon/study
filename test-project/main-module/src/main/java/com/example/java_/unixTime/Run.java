package com.example.java_.unixTime;

public class Run {
	public static void main(String[] args) {
		int minute = 1000 * 60;
		int hour = minute * 60;
		int day = hour * 24;
		int n2 = 2;
		int n10 = 10;
		int n12 = 12;
		int n18 = 18;

		System.out.println(n2 + "분 전: " + (System.currentTimeMillis() - minute * n2));
		System.out.println(n10 + "분 전: " + (System.currentTimeMillis() - minute * n10));
		System.out.println("1시간 전: " + (System.currentTimeMillis() - hour));
		System.out.println(n10 + "시간 전: " + (System.currentTimeMillis() - hour * n10));
		System.out.println(n12 + "시간 전: " + (System.currentTimeMillis() - hour * n12));
		System.out.println(n18 + "시간 전: " + (System.currentTimeMillis() - hour * n18));
		System.out.println("1일 전: " + (System.currentTimeMillis() - day));
		System.out.println(n2 + "일 전: " + (System.currentTimeMillis() - day * n2));
		System.out.println("현재 시각: " + System.currentTimeMillis());
	}
}
