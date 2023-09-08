package com.example.java_.math.round.t1;

public class Run {
	public static void main(String[] args) {

		double d = 12.34;

		System.out.println(Math.ceil(d));
		System.out.println(Math.round(d));
		System.out.println(Math.floor(d));
		System.out.println(String.format("%.0f",d));

		double d2 = -12.56;

		System.out.println(Math.ceil(d2));
		System.out.println(Math.round(d2));
		System.out.println(Math.floor(d2));
		System.out.println(String.format("%.1f",d2));

		double d3 = 1234567.456;
		String d3format = String.format("%,.2f", d3);
		System.out.println(d3format);
//		double d3change = Double.parseDouble(String.format("%,.2f", d3)); // NumberFormatException
		double d3change = Double.parseDouble(String.format("%.2f", d3));
		System.out.println(d3change);

	}
}
