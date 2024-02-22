package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonSetter;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Student {
	public String name;
	private int age;

	@JsonSetter("theName")
	public void setTheName(String name) {
		this.name = name;
	}

	@JsonSetter("age")
	public void setTheAge(int age) {
		this.age = age;
	}

}
