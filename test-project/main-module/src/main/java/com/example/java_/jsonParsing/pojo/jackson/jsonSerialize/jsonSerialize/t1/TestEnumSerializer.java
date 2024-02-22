package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.jsonSerialize.t1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

class TestEnumSerializer extends JsonSerializer<TestEnum> {
	@Override
	public void serialize(TestEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.getValue());
	}
}
