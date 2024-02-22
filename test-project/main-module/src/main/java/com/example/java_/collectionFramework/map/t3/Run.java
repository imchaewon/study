package com.example.java_.collectionFramework.map.t3;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		Map<String,Object> map = new HashMap<>();

		map.put("a","aaa");
		map.put("b","bbb");

		System.out.println(map);

		map.put("b","ccc");

	}
}
