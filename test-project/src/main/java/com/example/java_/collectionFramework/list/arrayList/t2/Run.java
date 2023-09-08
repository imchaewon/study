package com.example.java_.collectionFramework.list.arrayList.t2;

import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		new Run().m1();
	}

	public void m1(){
		List<String> list = Arrays.asList("가","나","다");
		list.forEach(a -> {
			System.out.println(a);
		});
		System.out.println();
		list.forEach(this::m2);
	}

	public void m2(String s){
		System.out.println(s);
	}
}
