package com.example.java_.stringBuilder.t1;

public class Run {
	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();

		sb.append("bc");
		sb.append(1);
		sb.insert(0,"a");
		sb.delete(1,2);
		System.out.println(sb.indexOf("c"));
		sb.deleteCharAt(1);
		sb.replace(0,1,"qq");
		System.out.println(sb.length());
		sb.setCharAt(1,'x');
//		sb.setLength(10);
//		sb.setCharAt(5,'g');
		sb.reverse();

		System.out.println(sb);

	}
}
