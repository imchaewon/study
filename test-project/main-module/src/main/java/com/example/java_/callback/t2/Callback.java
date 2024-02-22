package com.example.java_.callback.t2;

import gwt.material.design.jquery.client.api.Functions;

public class Callback {
	public static void m1(String s1, Functions.Func1<String> callback) {
		System.out.println("m1 start...");
		System.out.println(s1);
		callback.call("zzz");
	}
}
