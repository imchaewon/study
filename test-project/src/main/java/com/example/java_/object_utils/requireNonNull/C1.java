package com.example.java_.object_utils.requireNonNull;

import java.util.Objects;

public class C1 {
	String name;

	void setName(String name){
		if(name == null)
			throw new NullPointerException("name must not be null");

		this.name = name;
	}

	void setName2(String name) {
		this.name = Objects.requireNonNull(name, "name must not be null");
	}
}
