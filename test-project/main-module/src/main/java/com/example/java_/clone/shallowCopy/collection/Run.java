package com.example.java_.clone.shallowCopy.collection;

import org.json.JSONObject;

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

		list2 = list;

		list2.add("c");

		System.out.println(list2);
		System.out.println(list);


		System.out.println("-----");


		Map<String,Object> map = new HashMap<>();

		map.put("a","aaa");
		map.put("b","bbb");

		System.out.println(map);

		Map<String,Object> map2 = new HashMap<>();

		map2 = map;

		map2.put("c","ccc");

		System.out.println(map2);
		System.out.println(map);


		System.out.println("-----");


		JSONObject obj = new JSONObject();

		obj.put("a","aaa");
		obj.put("b","bbb");

		System.out.println(obj);

		JSONObject obj2 = new JSONObject();

		obj2 = obj;

		obj2.put("c","ccc");

		System.out.println(obj2);
		System.out.println(obj);






	}
}
