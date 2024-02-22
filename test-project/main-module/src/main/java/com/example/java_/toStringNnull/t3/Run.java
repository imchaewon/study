package com.example.java_.toStringNnull.t3;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		String s1 = "asd";
		String s2 = "as";
		String s3 = null;

		System.out.println(s1 == "asd");
		System.out.println(s1 == s2 + "d");
		System.out.println(s1.equals(s2 + "d"));
		System.out.println(s1.toString() == s1);
		System.out.println(s3 == null);
//		System.out.println(s3.toString());

		Map<String, Object> map = new HashMap<>();
		map.put("d", map);
		System.out.println(map.get("asd") == null);
		System.out.println("asd:::"+map.get("a") == null);
		System.out.println("asd:::"+map.get("d") == null);

	}
}
