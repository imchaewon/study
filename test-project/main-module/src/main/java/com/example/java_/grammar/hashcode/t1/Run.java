package com.example.java_.grammar.hashcode.t1;

public class Run {
	public static void main(String[] args) {

		System.out.println(m1("asd"));
		System.out.println(m1("aaa"));

	}
	static int m1(String s){
		return s.hashCode();
	}
}
