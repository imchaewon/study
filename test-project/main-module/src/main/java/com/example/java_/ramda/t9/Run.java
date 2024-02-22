package com.example.java_.ramda.t9;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		changeTxt("a",1,2,3);
	}

	private static I1[] changeTxt(String txt, Integer ... num) {
		return Arrays.stream(num).map(e -> (I1) e2 -> Integer.parseInt(txt)+1).toArray(I1[]::new);
	}
}

interface I1{
	public int m1(int a);
}