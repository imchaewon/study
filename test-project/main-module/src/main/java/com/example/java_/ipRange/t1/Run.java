package com.example.java_.ipRange.t1;

public class Run {
	public static void main(String[] args) {
		long userIP = 3232235600L;  // 사용자의 IP 주소
		long startIP = 3232235521L; // 시작 IP 주소
		long endIP = 3232235775L;   // 끝 IP 주소

		System.out.println("userIntegerToIP = "+ integerToIP(userIP));
		System.out.println("startIntegerToIP = " + integerToIP(startIP));
		System.out.println("endIntegerToIP = " + integerToIP(endIP));

		if (isIPInRange(userIP, startIP, endIP)) {
			System.out.println("사용자의 IP 주소는 대역에 포함됩니다.");
		} else {
			System.out.println("사용자의 IP 주소는 대역에 포함되지 않습니다.");
		}

		String startIPStr = "192.168.0.1";  // 시작 IP 주소
		String endIPStr = "192.168.0.255";  // 끝 IP 주소

		long[] range = ipRangeToInteger(startIPStr, endIPStr);
		long start = range[0];
		long end = range[1];

		System.out.println("시작 IP 주소 정수 값: " + start);
		System.out.println("끝 IP 주소 정수 값: " + end);
	}

	public static boolean isIPInRange(long ip, long startIP, long endIP) {
		return (ip >= startIP && ip <= endIP);
	}

	// IP 범위를 시작 주소와 끝 주소의 정수 값으로 변환하는 메서드
	public static long[] ipRangeToInteger(String startIP, String endIP) {
		long start = ipToInteger(startIP);
		long end = ipToInteger(endIP);
		return new long[]{start, end};
	}

	// IP 주소를 정수로 변환하는 메서드
	public static long ipToInteger(String ipAddress) {
		String[] ipAddressInArray = ipAddress.split("\\.");
		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {
			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);
			result += ip * Math.pow(256, power);
		}
		return result;
	}

	// 정수를 IP 주소로 변환하는 메서드
	public static String integerToIP(long ipAddress) {
		StringBuilder sb = new StringBuilder(15);
		for (int i = 0; i < 4; i++) {
			sb.insert(0, Long.toString(ipAddress & 0xff));
			if (i < 3) {
				sb.insert(0, '.');
			}
			ipAddress >>= 8;
		}
		return sb.toString();
	}
}