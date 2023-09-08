package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {

		String jsonStr = "{\n" +
				"  \"name\": \"zooneon\",\n" +
				"  \"age\": 25,\n" +
				"  \"address\": \"seoul\",\n" +
				"  \"contact\": {\n" +
				"    \"phone_number\": \"0102222\",\n" +
				"    \"email\": \"foo@google.com\"\n" +
				"  },\n" +
				"  \"job\": {\n" +
				"    \"working\": true,\n" +
				"    \"workplace\": {\n" +
				"      \"name\": \"Sejong Univ.\",\n" +
				"      \"position\": \"student\"\n" +
				"    }\n" +
				"  }\n" +
				"}";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Person person = objectMapper.readValue(jsonStr, Person.class);
		System.out.println(person);

	}
}
