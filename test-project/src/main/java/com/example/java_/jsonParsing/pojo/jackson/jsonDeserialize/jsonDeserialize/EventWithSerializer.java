package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonDeserialize;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class EventWithSerializer {
	private String name;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date eventDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
