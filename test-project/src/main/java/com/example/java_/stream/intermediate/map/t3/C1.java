package com.example.java_.stream.intermediate.map.t3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C1 {
	void m1(){

		List<String> list = Arrays.asList("가","나","다");

		List<String> collect = list.stream().map(this::m2).collect(Collectors.toList());
		System.out.println(collect);


	}

	String m2(String s){
		return s+"zzzzzzzz";
	}
}
