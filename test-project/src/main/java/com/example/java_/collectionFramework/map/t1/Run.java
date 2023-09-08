package com.example.java_.collectionFramework.map.t1;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>(3);

		map.put("a", "aa");
		System.out.println(map);
		map.clear();
		System.out.println(map);
		System.out.println(map.isEmpty());
		Map<String,Object> map2 = new HashMap<>(map);
		map.put("b", "bb");
		map2.put("c","cc");
		System.out.println(map2.containsKey("c"));
		System.out.println(map.containsKey("c"));
		System.out.println(map2.containsValue("cc"));
		System.out.println(map.containsValue("cc"));

		map2.put("d","dd");
		System.out.println(map2.entrySet());
		map.putAll(map2);
		System.out.println("map: " + map);
		System.out.println("map2: " + map2);

		System.out.println(map.get("d"));
		System.out.println(map.get("z"));
		System.out.println(map.getOrDefault("z", "zzz"));

		System.out.println(map.keySet());
		System.out.println(map.values());
		System.out.println(map.remove("d"));
		System.out.println(map);

		System.out.println(map.replace("c", "asdf"));
		System.out.println(map);
		System.out.println(map.put("c","zzz"));
		System.out.println(map);
		System.out.println(map.replace("c","asd","zxcv"));
		System.out.println(map);
		System.out.println(map.replace("c","zzz","zxcv"));
		System.out.println(map);

		System.out.println(map.size());
	}
}
