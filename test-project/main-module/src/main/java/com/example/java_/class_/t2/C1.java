package com.example.java_.class_.t2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class C1 {
	private String name;

	protected void m1(){
		System.out.println(name);
	}
}
