package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Circle extends Shape {
	private int radius;

	public Circle(String type, int radius) {
		setType(type);
		this.radius = radius;
	}
}
