package com.example.java_.class_.equals.t1;

import java.util.Objects;

public class C1 {

	String name;

	public C1(String name) {
		if(name == null)
			throw new NullPointerException();
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof C1)
			return name.equalsIgnoreCase(((C1) o).name);
		if (o instanceof String)
			return name.equalsIgnoreCase((String) o);
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
