package com.example.java_.class_.hash.map.t3;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		Map<String,Object> map = new HashMap<>();

		System.out.println(map.get("asd"));
		System.out.println(map.get("asd") == null);
		System.out.println(map.containsKey("asd"));

	}
}
