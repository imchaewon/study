package com.example.java_.collectionFramework.list.arrayList.t7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Run {
	public static void main(String[] args) {
		C1.m1();
	}

	private static class C1 {
		static List<Integer> list;

		static void m1() {
			System.out.println("list == null: " + (list == null));
			System.out.println("list.toString().equals(\"[]\"): " + Optional.ofNullable(list).toString().equals("[]"));

			list = new ArrayList<>();
			System.out.println("초기화");

			System.out.println("list == null: " + (list == null));
			System.out.println("list.isEmpty(): " + list.isEmpty());
			System.out.println("list.toString().equals(\"[]\"): " + list.toString().equals("[]"));

			list.add(1);
			System.out.println("1 삽입");

			System.out.println("list.isEmpty(): " + list.isEmpty());
		}
	}
}
