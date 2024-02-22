package com.example.java_.optional.t4;

import com.example.java_.SampleJSONDataInclusiveNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class Run {
	public static void main(String[] args) {

		JSONObject data = SampleJSONDataInclusiveNull.getData();
		JSONArray arr = data.getJSONArray("data");

		arr.forEach(i -> {
			JSONObject obj = (JSONObject)i;

			System.out.println(obj.getString("no"));
			System.out.println(obj.getString("name"));
			System.out.println(obj.getString("gender"));
			System.out.println(obj.getString("birth"));
			System.out.println(obj.getString("height"));
			System.out.println(obj.getString("weight"));
			System.out.println(obj.getString("address"));

			System.out.println("-----");

		});



	}
}
