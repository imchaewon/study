package com.example.java_.class_.hash.map.t2;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<>(){{
			put("a","aa");
			put("b", new HashMap<>(){{
				put("aa","aaa");
				put("bb","bbb");
			}});
		}};

		m1(map);

	}

	private static void m1(Map<String, Object> map){
		Object a = map.get("b");
		System.out.println(a);

		String s = String.valueOf(((Map<String,Object>) a).get("aa"));
		System.out.println(s);
	}
}



