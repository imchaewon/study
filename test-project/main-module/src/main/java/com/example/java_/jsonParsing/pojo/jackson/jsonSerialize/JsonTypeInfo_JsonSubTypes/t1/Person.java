package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.JsonTypeInfo_JsonSubTypes.t1;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.ToString;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "event_name", defaultImpl = Undefined.class)
@JsonSubTypes({
		@JsonSubTypes.Type(name = "moreInfo", value = MoreInfo.class),
		@JsonSubTypes.Type(name = "School", value = School.class),
})
public class Person {
	String name;
	int age;
	MoreInfo moreInfo;

	School school;

	public Person(String name, int age, MoreInfo moreInfo, School school) {
		this.name = name;
		this.age = age;
		this.moreInfo = moreInfo;
		this.school = school;
	}

}
