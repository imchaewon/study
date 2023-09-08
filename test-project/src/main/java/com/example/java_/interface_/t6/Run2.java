package com.example.java_.interface_.t6;

import java.util.ArrayList;

public class Run2 {
	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();

		Run1 r = new Run1();
		r.m1(list);

	}

	void m1(ArrayList<String> list){
		ArrayList<String> l = list;
		l.add("a");
		l.add("b");

		System.out.println(l);

	}
}
