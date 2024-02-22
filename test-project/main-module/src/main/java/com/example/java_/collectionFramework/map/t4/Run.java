package com.example.java_.collectionFramework.map.t4;

import java.util.*;

public class Run {
	public static void main(String[] args) {

		List<Map<String,Object>> list = new ArrayList<>();

		Map<String,Object> map = new HashMap<>();

		map.put("name","김준수");
		map.put("age",12);

		list.add(map);

		Map<String,Object> map2 = new HashMap<>();

		map2.put("name","한가인");
		map2.put("age",10);
		map2.put("gender",null);

		list.add(map2);

		System.out.println(list);

		System.out.println("------------------------------");

		list.forEach(i -> {
			System.out.println(i.size());
			System.out.println(i.containsKey("gender"));
			System.out.println(i.get("name"));
			System.out.println(i.getOrDefault("aaa",-1));
			System.out.println(i.keySet());
			System.out.println(i.values());
			System.out.println(i.entrySet());
			Set<Map.Entry<String, Object>> e = i.entrySet();
			e.forEach(i2 -> {
				System.out.println("-----");
				System.out.println(i2.getKey());
				System.out.println(i2.getValue());
				System.out.println("-----");
			});

		});

		System.out.println("------------------------------");

		System.out.println(map2.put("name","aaaaaaaaa"));
		System.out.println(map2.remove("name"));
		System.out.println(map2);

	}
}
