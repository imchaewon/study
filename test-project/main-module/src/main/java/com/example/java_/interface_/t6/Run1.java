package com.example.java_.interface_.t6;

import java.util.ArrayList;
import java.util.List;

public class Run1 {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		Run1 r = new Run1();
		r.m1(list);

	}

	void m1(List<String> list){
		ArrayList<String> l = (ArrayList<String>) list;
		l.add("a");
		l.add("b");

		System.out.println(l);

	}
}
