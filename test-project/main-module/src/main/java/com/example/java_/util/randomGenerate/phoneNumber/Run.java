package com.example.java_.util.randomGenerate.phoneNumber;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String phoneNumber = generateRandomPhoneNumber();
			System.out.println(phoneNumber);
		}
	}

	public static String generateRandomPhoneNumber() {
		Random rand = new Random();
		// 휴대전화 번호 형식: 010-xxxx-xxxx
		int secondSegment = rand.nextInt(10000); // 0000 ~ 9999
		int thirdSegment = rand.nextInt(10000); // 0000 ~ 9999

		// 랜덤한 두 번째 세그먼트를 만들 때 네 자리가 되도록 0을 채움
		String secondSegmentStr = String.format("%04d", secondSegment);
		// 랜덤한 세 번째 세그먼트를 만들 때 네 자리가 되도록 0을 채움
		String thirdSegmentStr = String.format("%04d", thirdSegment);

		String phoneNumber = "018-" + secondSegmentStr + "-" + thirdSegmentStr;
		return phoneNumber;
	}
}