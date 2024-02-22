package com.example.java_.class_.initializationBlock.t8;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
//		C1 c1 = null;
		C1 c2 = new C1();
		List l = C1.list2;
	}
}

class C1{
	List<String> list;
	static List<String> list2 = new ArrayList<>();

	static{
		System.out.println("class initial block");
		System.out.println("list: "+list2);
	}

	{
		System.out.println("instance initial block");
		System.out.println("list: "+list);
		list = new ArrayList<>();
	}

	public C1(){
		System.out.println("initial constructor");
		System.out.println("list: "+list);
	}
}
