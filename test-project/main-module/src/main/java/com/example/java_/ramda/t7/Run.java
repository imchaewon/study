package com.example.java_.ramda.t7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {

	public static void main(String[] args) {

//		m1();
		m2();

	}

	private static void m2() {
		Map<String, Integer> map = new HashMap<>();

		map.put("aaa",10);
		map.put("bbb",20);
		map.put("ccc",30);

		map.forEach((key,val) -> {
			if (val >= 20) {
				System.out.println("20이상!");
			}else {
				System.out.printf("key:%s value:%d\n", key, val);}
			}
		);

	}

	private static void m1() {
		List<String> list = Arrays.asList("a","Asdf","zxcv","qwer");

		list.forEach(System.out::println);
	}


}
