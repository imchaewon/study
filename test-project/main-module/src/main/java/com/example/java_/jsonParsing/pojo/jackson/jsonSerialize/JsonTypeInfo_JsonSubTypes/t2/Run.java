package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Run {
	public static void main(String[] args) throws Exception{
		Map<String, Shape> map = new HashMap<>();
		map.put("shape1", new Rectangle("rectangle", 10, 20));
		map.put("shape2", new Circle("circle", 5));
		System.out.println(map);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(map);
		System.out.println(json);

		Map<String, Shape> mapp = objectMapper.readValue(json, new TypeReference<Map<String, Shape>>(){});
		System.out.println(mapp);
		System.out.println(objectMapper.writeValueAsString(mapp));
	}
}
