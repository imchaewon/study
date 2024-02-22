package com.example.java_.stringUtils.matches.t1;

public class Run {
	public static void main(String[] args) {

		System.out.println("<br>".matches("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>"));
		System.out.println(">".matches("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>"));
		System.out.println("asdf".matches("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>"));
		System.out.println("<p>".matches("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>"));
		System.out.println("</p>".matches("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>"));

		System.out.println();

		System.out.println("<p><br></p>".replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>","").equals(""));
		System.out.println("<p>asdf<br></p>".replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>","").equals(""));

	}
}
