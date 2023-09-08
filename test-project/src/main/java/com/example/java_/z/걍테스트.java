package com.example.java_.z;

public class 걍테스트 {
	public static void main(String[] args) {
		for (int i = 9; i <= 11; i++) System.out.println(i);// 10월~12월
		System.out.println();

		for (int i = 9; i <= 12; i++) System.out.println(i);// 10월~1월(이월) 0+12
		System.out.println();



		for (int i = 9; i <= 12; i++) { // 10월~1월(이월) 0+12
			System.out.println(i % 12);
		}
		System.out.println();

		System.out.println((C1) null);
		System.out.println(String.valueOf((C1) null).equals("null"));
	}
}
