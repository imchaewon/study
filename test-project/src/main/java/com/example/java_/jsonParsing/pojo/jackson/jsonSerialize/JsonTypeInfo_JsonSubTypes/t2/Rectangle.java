package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rectangle extends Shape {
	private int width;
	private int height;

	public Rectangle(String type, int width, int height) {
		setType(type);
		this.width = width;
		this.height = height;
	}
}
