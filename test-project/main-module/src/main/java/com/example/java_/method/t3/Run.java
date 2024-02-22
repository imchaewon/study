package com.example.java_.method.t3;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		Map<String,Object> map = new HashMap<>();

		map.put("a","aaa");

		System.out.println(map);

		m1(map);

		System.out.println(map);

	}

	private static void m1(Map<String, Object> map) {
		map.put("b","bbb");
	}
}
