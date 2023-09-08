package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
		use= JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type",
		visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
		@JsonSubTypes.Type(value = Circle.class, name = "circle"),
})
public abstract class Shape {
	private String type;
}
