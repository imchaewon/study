package com.example.java_.jsonParsing.org_json.jsonobj_in_jsonobj;

import org.json.JSONArray;
import org.json.JSONObject;

public class Run {
	public static void main(String[] args) {

		JSONObject obj = new JSONObject();

		obj.put("a", "aaa");

		JSONObject obj_2 = new JSONObject();
		obj_2.put("b2", "b222");
		obj_2.put("b3", "b333");
		obj_2.put("b4", "b444");

		obj.put("b", obj_2);

		obj.put("c", "ccc");

		JSONArray arr = new JSONArray(); // 맞나?
		obj.append("d", arr); // append는 배열을 추가할때 씀

		System.out.println(obj);

		System.out.println("----------------------------------------------------------------------------------------------------");

		System.out.println(obj.has("b")); // 키가 있는지
		System.out.println(obj.get("b")); // 찾기
		System.out.println(obj.getJSONObject("b")); // 찾은 후 JSONObject로 형변환

		System.out.println(obj.has("b2")); // 손자로 건너뛰는 탐색은 불가능

	}
}
