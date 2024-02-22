package com.example.java_.class_.t5;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class C2 {
	String s1;
	@ToString.Exclude
	List<C1> list = new ArrayList<>();

	public List<C1> getList() {
		return list;
	}

}
