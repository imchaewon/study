package com.example.java_.jsonNode.t3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) throws IOException {

		URL url = new URL("https://jsonplaceholder.typicode.com/comments");
		System.out.println(url);

		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> list = mapper.readValue(url, new TypeReference<>(){});
		System.out.println(list);

		System.out.println("----------");

		JsonNode jsonNode = mapper.readTree(url);
		System.out.println(jsonNode);

	}
}
