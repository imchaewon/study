package com.example.java_.regex.matcher.t1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Run {
	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("x (a\\w*f)");

		Matcher matcher = pattern.matcher("xxxx axxxzfxx");
		Matcher matcher2 = pattern.matcher("x axxxxf");

		System.out.println("matcher.matches: " + matcher.matches());
		System.out.println("matcher2.matches: " + matcher2.matches());
		System.out.println("matcher.find: " + matcher.find());
		System.out.println("matcher.find(3): " + matcher.find(3));
		System.out.println("matcher.start: " + matcher.start());
		System.out.println("matcher.start(1): " + matcher.start(1));
		System.out.println("matcher.end: " + matcher.end());
		System.out.println("matcher.end(1): " + matcher.end(1));
		System.out.println("matcher.group: " + matcher.group());
		System.out.println("matcher.group(1): " + matcher.group(1));
		System.out.println("matcher.groupCount: " + matcher.groupCount());

	}
}
