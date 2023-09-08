package com.example.java_.class_.hash.t1;

import java.util.Objects;

public class C2 {
	public String name;

	public C2(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
