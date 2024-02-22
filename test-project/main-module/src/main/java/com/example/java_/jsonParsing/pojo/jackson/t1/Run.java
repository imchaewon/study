package com.example.java_.jsonParsing.pojo.jackson.t1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {

		// Serializer
		Model model = new Model(1, TestEnum.TEST_1);
		System.out.println(new ObjectMapper().writeValueAsString(model));

		System.out.println("-----");

		// Deserializer
		String json = "{\"num\":1, \"test\":\"tmpTestValue1\"}";
		Model model2 = new ObjectMapper().readValue(json, Model.class);
		System.out.println(model2.getNum());
		System.out.println(model2.getTest());

	}
}
