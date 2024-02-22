package com.example.java_.interface_.t9;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class C1 {
	private Inter<JsonNode> inter;
	private static final ObjectMapper objectMapper = null;

	void c1m1(){
		JsonNode jsonNode = inter.m1("이름",123);
		try {
			System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		C1 c = new C1();
		c.c1m1();
	}
}
