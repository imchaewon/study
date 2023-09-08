package com.example.java_.class_.hash.set;

import java.util.HashSet;
import java.util.Set;

public class Run {
	public static void main(String[] args) {

		C1 o1 = new C1("aa");
		C1 o2 = new C1("bb");
		C1 o3 = new C1("aa");

		Set<C1> set = new HashSet<>();
		set.add(o1);
		set.add(o2);
		set.add(o3);

		System.out.println(set);
		System.out.println(set.size());

		C2 o4 = new C2("aa");
		C2 o5 = new C2("bb");
		C2 o6 = new C2("aa");

		Set<C2> set2 = new HashSet<>();
		set2.add(o4);
		set2.add(o5);
		set2.add(o6);

		System.out.println(set2);
		System.out.println(set2.size());

	}
}
