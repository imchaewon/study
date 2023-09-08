package com.example.java_.collectionFramework.list.arrayList.unmodifiableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");

		try {
			list = Collections.unmodifiableList(list); // 추가, 삭제 행위가 금지됨
			list.add("e");
		} catch (Exception e) {
			System.out.println("Run.main()");
			e.printStackTrace();
		}
		System.out.println(list);
		try {
			list.remove(0);
		} catch (Exception e) {
			System.out.println("Run.main()");
			e.printStackTrace();
		}





	}
}
