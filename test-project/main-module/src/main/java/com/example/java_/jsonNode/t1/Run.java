package com.example.java_.jsonNode.t1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Run {
	public static void main(String[] args) {

		Student student = new Student();
		student.name = "최낙범";
		Teacher teacher = new Teacher();
		teacher.human = new Human("김준수",20);
		teacher.gender = "m";
		School school = new School();
		school.student = student;
		school.teacher = teacher;
		school.schoolFlower = "개나리";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

		JsonNode jsonNode1 = objectMapper.valueToTree(school);
		JsonNode jsonNode2 = objectMapper.convertValue(school, JsonNode.class);

		System.out.println("jsonNode1: "+jsonNode1);
		System.out.println("jsonNode2: "+jsonNode2);

		System.out.println("-----");

		System.out.println(jsonNode1.findPath("teacher"));
		System.out.println(jsonNode1.findPath("gender"));
		System.out.println(jsonNode1.findPath("name"));

		System.out.println("-----");

		System.out.println(jsonNode1.get("name"));
		System.out.println(jsonNode1.get("teacher"));
		System.out.println(jsonNode1.get("teacher").get("human"));

		System.out.println("-----");

		System.out.println(jsonNode1.findPath("asd"));
		System.out.println(jsonNode1.findPath("asd").toString().equals(""));
		System.out.println(jsonNode1.get("asd"));
		System.out.println(jsonNode1.path("asd").getClass());
		System.out.println(jsonNode1.path("asd").isMissingNode());

		System.out.println("-----");

		try {
			JsonNode node = objectMapper.readTree(String.valueOf(jsonNode1));
			System.out.println(node);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
