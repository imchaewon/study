package com.example.java_.interface_.t9;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface Inter<T> {

	@Getter
	@ToString
	@Builder
	class C1 {
		private String name;
		private int age;
	}

	T m1(String name, int age);

	default T m1() {
		return m1("xxx",111);
	}

}
