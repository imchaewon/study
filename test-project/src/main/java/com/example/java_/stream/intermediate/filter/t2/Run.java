package com.example.java_.stream.intermediate.filter.t2;

import com.example.java_.SampleJSONData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {

		JSONArray arr = (JSONArray) SampleJSONData.getData().get("data");

		System.out.println(arr);
		System.out.println(((JSONObject)arr.get(0)).getString("address"));

		List<Map<String,Object>> list = new ArrayList<>();
		arr.forEach(i -> {
			JSONObject obj = (JSONObject) i;
			list.add(obj.toMap());
		});

		System.out.println(list);

		List<Map<String,Object>>list2 = list .stream().filter(i -> Integer.parseInt(i.get("height").toString()) < 170).collect(Collectors.toList());

		System.out.println(list2);


	}
}
