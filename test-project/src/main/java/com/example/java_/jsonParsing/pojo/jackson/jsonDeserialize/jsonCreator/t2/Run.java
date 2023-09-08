package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"id\":25,\"name\":\"Sean\"}";
		ObjectMapper mapper = new ObjectMapper();
		Student student = mapper
				.readerFor(Student.class)
				.readValue(json);
		System.out.println(student);
	}
}

