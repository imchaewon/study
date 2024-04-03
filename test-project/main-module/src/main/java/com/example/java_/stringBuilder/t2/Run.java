package com.example.java_.stringBuilder.t2;

public class Run {
	public static void main(String[] args) {
		new Run().m1();
	}

	public void m1() {
		StringBuilder sb1 = new StringBuilder("a");
		m2(sb1);

		System.out.println("sb1 = " + sb1);
	}

	public void m2(StringBuilder s1){
		s1.append("sdf");
	}
}
