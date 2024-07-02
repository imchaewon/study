package com.example.java_.util.randomGenerate.macAddress;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String macAddress = generateRandomMacAddress();
			System.out.println(macAddress);
		}
	}

	public static String generateRandomMacAddress() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(String.format("%02X", rand.nextInt(256))); // 두 자리 16진수로 변환하여 추가
			if (i < 5) {
				sb.append(":"); // 맨 마지막 세그먼트를 제외한 각 세그먼트 뒤에 콜론 추가
			}
		}
		return sb.toString();
	}
}