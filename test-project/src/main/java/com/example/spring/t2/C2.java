package com.example.spring.t2;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C2 implements C1{

	@Autowired
	private C3 c3;

	@Override
	public JSONObject test1() {
		return c3.m1();
	}
}
