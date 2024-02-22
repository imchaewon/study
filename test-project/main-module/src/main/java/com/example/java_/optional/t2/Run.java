package com.example.java_.optional.t2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Run {
	public static void main(String[] args) {
		new Run().m1();
	}

	void m1(){
		String a = "aaa";
		String b = "bbb";
		String c = null;
		String d = "";

		List<String> list = Arrays.asList(a,b,c,d);

		list.forEach(this::m2);
	}

	void m2(String i){
		Optional<String> opt = Optional.ofNullable(i);
		System.out.println(opt);
	}

}
