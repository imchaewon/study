package com.example.java_.date.utcAndLocal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Run {
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis() / 1000);

		Date currentDate = new Date();

		ZonedDateTime localDateTime = ZonedDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		System.out.println("현재 지역 시각(ZonedDateTime): " + localDateTime);

		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		System.out.println("현재 지역 시각(SimpleDateFormat): " + dateFmt.format(currentDate));

		LocalDate localDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		System.out.println("현재 지역 시각(LocalDate/LocalTime): " + localDate + currentTime);
	}
}
