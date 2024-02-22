package com.example.java_.class_.hash.map.t1;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		Map<Object,String> map = new HashMap<>();

		map.put(new C1("aa"),"a");
		map.put(new C1("bb"),"b");
		map.put(new C1("aa"),"c");

		System.out.println(map);
		System.out.println(map.size());

		Map<Object,String> map2 = new HashMap<>();

		map2.put(new C2("aa"),"a");
		map2.put(new C2("bb"),"b");
		map2.put(new C2("aa"),"c");

		System.out.println(map2);
		System.out.println(map2.size());


	}
}
