package com.example.java_.testData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Human {
	public String name;
	public String gender;
	public String birth;
	public Integer height;
	public Double weight;
	public String address;
	private String _private;
	protected String _protected;

}
