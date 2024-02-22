package com.example.java_.gofDesignPatterns.creational.builder.t4;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class C1 {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
}
