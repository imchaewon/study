package com.example.java_.annotation.lombok.nonNull.t1;

import lombok.NonNull;
import lombok.ToString;

@ToString
public class C1 {
	String name;

	String address;

	@NonNull
	String gender = "m";
}
