package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.jsonSerialize.t1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {

		Model model = new Model(1, TestEnum.TEST_1);
		System.out.println(new ObjectMapper().writeValueAsString(model));

	}
}
