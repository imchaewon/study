package com.example.java_.class_.clone.t1;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class C1 implements Cloneable {
	public String name;
	public int age;

	public C1 clone() {
		C1 clone = null;
		try {
			clone = (C1) super.clone();
		} catch (Exception e) {
			System.out.println("C1.clone()");
			e.printStackTrace();
		}
		return clone;
	}
}
