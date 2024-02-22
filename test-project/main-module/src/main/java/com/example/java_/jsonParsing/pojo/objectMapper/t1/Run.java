package com.example.java_.jsonParsing.pojo.objectMapper.t1;

import com.example.java_.SampleJSONData2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) {

		String jsonStr = SampleJSONData2.getData().toString();
		System.out.println(jsonStr);

		ObjectMapper mapper = new ObjectMapper();

		try {
			Univ univ = mapper.readValue(jsonStr, Univ.class);
			System.out.println(univ);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
