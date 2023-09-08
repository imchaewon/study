package com.example.java_.logic.t2;

public class Run {
	public static void main(String[] args) {

		int i = 2;

		if(i > 0)
			if(i == 1)
				System.out.println("i == 1");
		else // else가 바깥쪽의 if와 짝지어져야할거같지만 가장 가까운 if에 짝지어짐
			System.out.println("i <= 0");

		System.out.println("----------------------------------------");

		int i2 = 2;

		if(i2 > 0) {
			if (i == 1)
				System.out.println("i == 1");
		}else // 바깥쪽 if를 블럭으로 감싸야함
			System.out.println("i <= 0");


	}
}
