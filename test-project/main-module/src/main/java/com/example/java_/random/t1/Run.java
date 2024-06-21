package com.example.java_.random.t1;


import java.util.Random;

public class Run {
	public static void main(String[] args) {

		// 두 문장은 동등함
		double randNum = Math.random();
		double randNum2 = new Random().nextDouble();

		System.out.println(randNum);
		System.out.println(randNum2);
	}
}