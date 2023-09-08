package com.example.java_.ramda.t6;

public class Run {

	public interface Lamd {
		public int call(String val);
	}

	public static void main(String[] args) {

		Lamd l = (s) -> s.length();

		System.out.println(l.call("asdf"));

	}

}
