package com.example.java_.memory.t1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) {
		Map map = new HashMap();
		List list = new ArrayList();

		map.put("k", "apple");

		list.add(map);

		map.put("k", "banana");

		list.add(map);

		System.out.println(list);
		System.out.println(map.hashCode());
	}
}
