package com.example.java_.annotation.suppressWarnings;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		m1();
		m2();
		System.out.println(m3(new ArrayList<String>()));
	}

	@SuppressWarnings("deprecation")
	private static void m1() {
		C1 c1 = new C1();
		c1.i = 1;
		c1.m1();
		System.out.println(c1);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static void m2() {
		List list = new ArrayList();
		list.add("asd");
		System.out.println(list);
	}

	@SafeVarargs
	private static <T> T m3(T ... list) {
		return list[0];
	}
}

class C1 {
	@Deprecated
	int i;

	@Deprecated
	void m1(){
	}
}