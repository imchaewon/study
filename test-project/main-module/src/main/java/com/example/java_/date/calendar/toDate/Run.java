package com.example.java_.date.calendar.toDate;

import java.util.Calendar;
import java.util.Date;

public class Run {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date d = new Date(c.getTimeInMillis());

		System.out.println(d);
	}
}
