package com.example.java_.collectionFramework.list.linkedList.t1;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		System.out.println("= 순차적으로 추가하기 =");
		long start = System.currentTimeMillis();
		List<Integer> arrayList = new ArrayList<>();
		for(int i=0;i<20000000;i++)
			arrayList.add(1);
		long end = System.currentTimeMillis();
		System.out.println("arrayList: " + (end - start));

		start = System.currentTimeMillis();
		List<Integer> linkedList = new ArrayList<>();
		for(int i=0;i<20000000;i++)
			linkedList.add(1);
		end = System.currentTimeMillis();
		System.out.println("linkedList: " + (end - start));

		System.out.println();
		System.out.println("= 중간에 추가하기 =");

		start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
			arrayList.add(100, 1);
		end = System.currentTimeMillis();
		System.out.println("arrayList: " + (end - start));

		start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
			linkedList.add(100, 1);
		end = System.currentTimeMillis();
		System.out.println("linkedList: " + (end - start));

		System.out.println();
		System.out.println("= 중간에서 삭제하기 =");

		start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
			arrayList.remove(100);
		end = System.currentTimeMillis();
		System.out.println("arrayList: " + (end - start));

		start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
			linkedList.remove(100);
		end = System.currentTimeMillis();
		System.out.println("linkedList: " + (end - start));

		System.out.println();
		System.out.println("= 순차적으로 삭제하기 =");

		start = System.currentTimeMillis();
		for(int i=arrayList.size()-1;i>=0;i--)
			arrayList.remove(i);
		end = System.currentTimeMillis();
		System.out.println("arrayList: " + (end - start));

		start = System.currentTimeMillis();
		for(int i=linkedList.size()-1; i>=0; i--)
			linkedList.remove(i);
		end = System.currentTimeMillis();
		System.out.println("linkedList: " + (end - start));
	}
}
