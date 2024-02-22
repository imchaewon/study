package com.example.java_.toStringNnull.t2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		System.out.println(list == null);
		System.out.println(list.isEmpty());
		System.out.println(list.toString() == "[]");

//		System.out.println(list.get(3));


		System.out.println("--------------------------------------------------");


		Map<String, Object> map = new HashMap<>();

		System.out.println(map == null);
		System.out.println(map.isEmpty());
		System.out.println(map.toString() == "{}");

		System.out.println(map.get("asd") == null);


		System.out.println("--------------------------------------------------");


		C1 c1 = new C1();

		System.out.println(c1 == null);
		System.out.println(c1.toString() == null);





	}
}
