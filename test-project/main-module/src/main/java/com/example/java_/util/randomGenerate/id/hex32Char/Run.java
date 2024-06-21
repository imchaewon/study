package com.example.java_.util.randomGenerate.id.hex32Char;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String id = generateRandomId();
			System.out.println(id);
		}
	}

	public static String generateRandomId() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			// 0부터 15까지의 랜덤한 숫자를 생성하여 16진수로 변환하여 추가
			int randomNumber = rand.nextInt(16);
			sb.append(Integer.toHexString(randomNumber));
		}
		return sb.toString();
	}
}