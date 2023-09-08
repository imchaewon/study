package com.example.java_.class_.t5;

import lombok.ToString;

@ToString
public class C1 {
	private C2 c2 = new C2();
	public void m1(){
		c2.getList().add(this);
		c2.s1 = "asdsad";
	}
}
