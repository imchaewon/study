package com.example.java_.jsonParsing.pojo.objectMapper.t1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Univ {
	String school;
	List<Human> humans;
	@ToString
	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class Human{
		String name;
		int age;
		int height;
		float weight;
	}
}
