package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jacksonInject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"name\":\"My bean\"}";

		InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1); // id값으로 1 주입
		Student bean = new ObjectMapper().reader(inject).forType(Student.class).readValue(json);

		System.out.println(bean);



	}
}

