package com.example.java_.clone.deepCopy.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		list.add("a");
		list.add("b");

		System.out.println(list);

		List<String> list2 = new ArrayList<>();

		list2 = (List<String>) ((ArrayList<String>)list).clone();

		list2.add("c");

		System.out.println(list2);
		System.out.println(list);


		System.out.println("-----");


		Map<String,Object> map = new HashMap<>();

		map.put("a","aaa");
		map.put("b","bbb");

		System.out.println(map);

		Map<String,Object> map2 = new HashMap<>();

		map2 = (Map<String,Object>)((HashMap<String,Object>)map).clone();

		map2.put("c","ccc");

		System.out.println(map2);
		System.out.println(map);


	}
}
