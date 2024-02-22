package com.example.java_.date.date.toCalendar;

import java.util.Calendar;
import java.util.Date;

public class Run {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		System.out.println(c);
	}
}
