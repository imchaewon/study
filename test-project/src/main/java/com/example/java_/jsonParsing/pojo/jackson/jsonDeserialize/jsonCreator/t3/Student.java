package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
class Student {
	public int id;
	public String name;

	@JsonCreator
	public static Student asd(@JsonProperty("rollNo") int rollNo, @JsonProperty("theName") String name){
		return new Student(rollNo,name);
	}

}
