package com.example.java_.equalsAndHashCode.t2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
//@EqualsAndHashCode(callSuper = false)
//@EqualsAndHashCode
@AllArgsConstructor
public class Child extends Parent {
	String cs1;
	int ci1;
}