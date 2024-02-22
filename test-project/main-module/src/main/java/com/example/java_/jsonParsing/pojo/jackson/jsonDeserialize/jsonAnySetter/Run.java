package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonAnySetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"name\":\"My bean\",\"attr1\":\"val1\",\"attr2\":\"val2\"}";

		Student bean = new ObjectMapper()
				.readerFor(Student.class)
				.readValue(json);

		System.out.println(bean);

		System.out.println(bean.getProperties().get("attr1"));

	}
}

