package com.example.java_.jsonParsing.pojo.jackson.jsonDeserialize.jsonDeserialize;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class Run {
	public static void main(String[] args) throws IOException {
		String json = "{\"name\":\"party\",\"eventDate\":\"2020-09-12 01:12:34\"}";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		EventWithSerializer event = new ObjectMapper()
				.readerFor(EventWithSerializer.class)
				.readValue(json);

		System.out.println(df.format(event.getEventDate()));

		assertEquals("2020-09-12 01:12:34", df.format(event.getEventDate()));
	}
}
