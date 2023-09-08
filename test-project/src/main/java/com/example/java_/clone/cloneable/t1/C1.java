package com.example.java_.clone.cloneable.t1;

import lombok.ToString;

@ToString
public class C1 implements Cloneable{
	String name;
	int age;

	public C1 clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (C1) obj;
	}
}
