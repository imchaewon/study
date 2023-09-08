package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"rollNo\":11,\"theName\":\"Mark\"}";
		ObjectMapper mapper = new ObjectMapper();
		Student student = mapper
				.readerFor(Student.class)
				.readValue(json); // 팩토리메소드로도 가능. @JsonCreator, @AllArgsConstructor 필요
		System.out.println(student);
	}
}

