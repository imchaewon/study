package com.example.java_.date.date.t1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Run {
	public static void main(String[] args) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateString = "20230101";
		Date date = dateFormat.parse(dateString);  // Date로 변환

		// 현재날짜가 지정일보다 이후이면
		if(new Date().after(date)){
			System.out.println("이후");
		}

		// 현재날짜가 지정일보다 이전이면
		else if(new Date().before(date)){
			System.out.println("이전");
		}

	}
}
