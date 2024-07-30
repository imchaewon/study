package com.example.java_.equalsAndHashCode.t3;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Child extends Parent {
	String cs1;
	int ci1;
}