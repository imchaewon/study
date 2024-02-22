package com.example.java_.class_.equals.object;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@AllArgsConstructor
public class C2 {
	public String name;
	public int age;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		C2 c2 = (C2) o;
		return age == c2.age && Objects.equals(name, c2.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}
}
