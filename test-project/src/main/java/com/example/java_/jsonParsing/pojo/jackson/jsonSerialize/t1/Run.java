package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.t1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Run {
	public static void main(String[] args) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		Person person = new Person("zooneon", 25, "seoul");

//		objectMapper.writeValue(new File("person.json"),person);
		String jsonString = objectMapper.writeValueAsString(person);
		System.out.println(jsonString); // json문자열로 출력

		jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
		System.out.println(jsonString); // 예쁘게 출력

	}
}
