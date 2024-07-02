package com.example.java_.util.randomGenerate.ipAddress.privateIpAddress;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String ipAddress = generateRandomIpAddress();
			System.out.println(ipAddress);
		}
	}

	// 랜덤 IP 주소 생성 메서드
	public static String generateRandomIpAddress() {
		Random rand = new Random();
		// 사설 IP 주소 범위: 10.0.0.0 ~ 10.255.255.255
		//                172.16.0.0 ~ 172.31.255.255
		//                192.168.0.0 ~ 192.168.255.255
		String[] ipRanges = {"10", "172", "192"};
		String ipAddress = ipRanges[rand.nextInt(ipRanges.length)] + "." +
				(rand.nextInt(256) - 1) + "." +
				rand.nextInt(256) + "." +
				rand.nextInt(256);
		return ipAddress;
	}
}