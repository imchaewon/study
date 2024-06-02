package com.example.webflux.utils;

public class TimeUtils {
	public static void sleep(long l) {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}