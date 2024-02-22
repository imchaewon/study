package com.example.java_.class_.hash.set;

import java.util.Objects;

public class C2 {
	String name;

	public C2(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		C2 c2 = (C2) o;
		return Objects.equals(name, c2.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
