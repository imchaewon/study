package com.example.java_.jsonObject.t1;

import org.json.JSONObject;

public class Run {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			JSONObject obj1 = new JSONObject();  // 객체 생성
			obj1.put("name", "감자");
			obj1.put("age", 12);
			// ...
			// 객체 사용
			// ...
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		System.out.println();

		start = System.currentTimeMillis();
		JSONObject obj2 = new JSONObject();  // 객체 생성
		for (int i = 0; i < 1000; i++) {
			obj2.put("name", "감자");
			obj2.put("age", 12);
			// ...
			// 객체 사용
			// ...
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);

	}
}
