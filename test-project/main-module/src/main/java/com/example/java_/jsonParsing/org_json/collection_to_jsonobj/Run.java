package com.example.java_.jsonParsing.org_json.collection_to_jsonobj;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Run {
	public static void main(String[] args) {

		Map<String, Object> map1 = new HashMap<>();
		map1.put("a", 1);
		map1.put("b", 2);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("c", 3);
		map2.put("d", 4);

		JSONObject jsonObj1 = new JSONObject(map1);

		System.out.println("map::: " + map1);
		System.out.println("jsonObj::: " + jsonObj1);


		List<Map<String,Object>> list = Arrays.asList(map1, map2);

		JSONArray jsonArr = new JSONArray(list);

		System.out.println("list::: " + list);
		System.out.println("jsonArr::: " + jsonArr);



	}
}
