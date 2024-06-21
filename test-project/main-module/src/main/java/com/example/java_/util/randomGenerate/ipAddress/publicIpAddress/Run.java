package com.example.java_.util.randomGenerate.ipAddress.publicIpAddress;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String publicIpAddress = generateRandomPublicIpAddress();
			System.out.println(publicIpAddress);
		}
	}

	public static String generateRandomPublicIpAddress() {
		Random rand = new Random();
		// 공인 IP 주소 범위: 1.0.0.0 ~ 223.255.255.255
		//                    224.0.0.0 ~ 239.255.255.255 (멀티캐스트 주소)
		//                    240.0.0.0 ~ 255.255.255.255 (사용 불가능 주소)
		int firstOctet = rand.nextInt(223) + 1; // 1 ~ 223
		int secondOctet = rand.nextInt(256); // 0 ~ 255
		int thirdOctet = rand.nextInt(256); // 0 ~ 255
		int fourthOctet = rand.nextInt(256); // 0 ~ 255
		String publicIpAddress = firstOctet + "." + secondOctet + "." + thirdOctet + "." + fourthOctet;
		return publicIpAddress;
	}
}