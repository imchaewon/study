package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {

		Person person = new Person("sean",11,new MoreInfo("m","q.q.com"),new School("seoulUniv","seoul"));
		System.out.println(person);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(person);
		System.out.println(json);


	}
}
