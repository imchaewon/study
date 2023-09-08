package com.example.java_.jsonParsing.pojo.jackson.t1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

class TestEnumDeserializer extends JsonDeserializer<TestEnum> {

	@Override
	public TestEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		for (TestEnum value : TestEnum.values()) {
			if (value.getValue().equals(p.getText())) {
				return value;
			}
		}

		return null;
	}
}
