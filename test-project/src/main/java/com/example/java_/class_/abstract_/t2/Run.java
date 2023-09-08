package com.example.java_.class_.abstract_.t2;

import org.json.JSONObject;

public class Run {
	public static void main(String[] args) {

		C1 c2 = new C2();

		JSONObject resultObject = new JSONObject();

		JSONObject object1 = new JSONObject();
		object1.put("process", "success");

		JSONObject object2 = new JSONObject();

		c2.m1(object1, object2);

		resultObject.put("header", object1);
		resultObject.put("body", object2);

		System.out.println("resultObject.........");
		System.out.println(resultObject);

	}
}
