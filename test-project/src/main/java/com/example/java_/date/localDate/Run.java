package com.example.java_.date.localDate;

import java.time.LocalDate;
import java.time.LocalTime;

public class Run {
	public static void main(String[] args) {

		LocalDate localDate = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2021, 2, 22);
		LocalTime currentTime = LocalTime.now();
		LocalTime currentTime2 = LocalTime.of(12, 01, 22);

		System.out.println(localDate);
		System.out.println(localDate2);
		System.out.println(currentTime);
		System.out.println(currentTime2);

	}
}
