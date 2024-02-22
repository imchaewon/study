package com.example.java_.typeReference.jsonstrToMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Run {
	public static void main(String[] args) throws JsonProcessingException {
		String json = "{\"id\":11,\"name\":\"Mark\"}";
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});

		System.out.println(map);
	}
}

