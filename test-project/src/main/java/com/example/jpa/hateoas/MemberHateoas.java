package com.example.jpa.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class MemberHateoas extends RepresentationModel<MemberHateoas> {

	private final String name;

	private final Integer age;

	//JSON 타입을 받을 JsonCreator 생성자 생성
	@JsonCreator
	public MemberHateoas(@JsonProperty("name") String name, @JsonProperty("age") Integer age) {
		this.name = name;
		this.age = age;
	}
}
