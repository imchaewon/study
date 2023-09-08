package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonSetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"theName\":\"My bean\",\"age\":11}";

		Student bean = new ObjectMapper()
				.readerFor(Student.class)
				.readValue(json);

		System.out.println(bean);

	}
}

