package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonAnySetter;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Getter
public class Student {
	public String name;
	private Map<String, String> properties = new HashMap<>();

	@JsonAnySetter
	public void add(String key, String value) {
		properties.put(key, value);
	}

}
