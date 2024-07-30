package com.example.webflux.utils;

public class TimeUtils {
	public static void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}