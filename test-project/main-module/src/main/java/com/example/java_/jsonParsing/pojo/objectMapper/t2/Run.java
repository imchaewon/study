package com.example.java_.jsonParsing.pojo.objectMapper.t2;

import com.example.java_.SampleJSONData2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Run {
	public static void main(String[] args) {

		String jsonStr = SampleJSONData2.getData().getJSONArray("humans").toString();
		System.out.println(jsonStr);

		ObjectMapper mapper = new ObjectMapper();

		try {
			List<Human> list = mapper.readValue(jsonStr, new TypeReference<List<Human>>() {});
			System.out.println(list);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
