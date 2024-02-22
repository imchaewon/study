package com.example.java_.jsonParsing.pojo.objectMapper.t2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Human {
	int no;
	String name;
	int height;
	float weight;
}
