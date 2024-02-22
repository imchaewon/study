package com.example.java_.decimalFormat.t1;

import java.text.DecimalFormat;

public class Run {
	public static void main(String[] args) {
		double number = 1234567.89;
//		DecimalFormat df = new DecimalFormat("#.#E0");
		DecimalFormat df = new DecimalFormat("#,##0.000");
//		DecimalFormat df = new DecimalFormat("#,##0.###");
		String result = df.format(number);

		System.out.println(result);
	}
}
