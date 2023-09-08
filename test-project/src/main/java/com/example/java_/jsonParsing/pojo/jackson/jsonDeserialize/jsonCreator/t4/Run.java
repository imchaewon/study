package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"id\":11,\"name\":\"Mark\"}";
		ObjectMapper mapper = new ObjectMapper();
		Student student = mapper
				.readValue(json, Student.class); // key와 필드명이 같다면 @JsonProperty / readerFor를 안쓰고 readValue메소드 만으로 가능
		System.out.println(student);
	}
}

