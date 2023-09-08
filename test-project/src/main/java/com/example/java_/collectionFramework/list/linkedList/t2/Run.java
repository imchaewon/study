package com.example.java_.collectionFramework.list.linkedList.t2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		List<Integer> arraylist = Arrays.asList(1,2,3);

		List<Integer> linkedList = new LinkedList<>(arraylist); // 생성자 사용해서 간단히 컬렉션간 이동가능
		System.out.println(linkedList);

		List<Integer> arrayList2 = new ArrayList<>(linkedList);
		System.out.println(arrayList2);
	}
}
