package com.example.java_.annotation.springframework.nonNull.t1;

import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@ToString
public class C1 {
	String name;

	@Nullable
	String address;

	@NonNull
	String gender = "m";
}
