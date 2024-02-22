package com.example.java_.collectionFramework.list.arrayList.t8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		List<Map<String, String>> test1 = new ArrayList<>();
		List<Map<String, String>> test2 = new ArrayList<>();
		Map<String, String> testMap = new HashMap<>();

		testMap.put("test1", "test1");
		testMap.put("test2", "test2");
		System.out.println("testMap: "+testMap);

		test1.add(testMap);
		System.out.println("test1: "+test1);

		test2.add(test1.get(0));
		test2.get(0).put("test1", "test111111111");
		System.out.println("test1: "+test1);
		System.out.println("test2: "+test2);

	}
}
