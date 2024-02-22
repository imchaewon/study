package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonCreator.t6;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Person {
	private String name;
	private Contact contact;
	private Job job;

	@Getter
	@ToString
	@NoArgsConstructor
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public class Contact {
		String phoneNumber;
		String email;
	}

	@ToString
	@Getter
	public class Job {
	}

}
