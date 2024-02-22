package com.example.java_.jsonParsing.pojo.jackson.jsonSerialize.jsonSerialize.t1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Model {

	private int num;

	@JsonSerialize(using = TestEnumSerializer.class)
	private TestEnum test;

}