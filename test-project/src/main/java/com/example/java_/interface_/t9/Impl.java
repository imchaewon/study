package com.example.java_.interface_.t9;

import org.json.JSONObject;

public class Impl implements Inter<JSONObject>{

	@Override
	public JSONObject m1(String name, int age) {
		JSONObject obj = new JSONObject();
		obj.put("name",name);
		obj.put("age",age);
		return obj;
	}

}
