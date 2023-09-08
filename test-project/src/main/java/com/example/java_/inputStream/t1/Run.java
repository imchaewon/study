package com.example.java_.inputStream.t1;

import java.io.ByteArrayInputStream;

public class Run {
	public static void main(String[] args) {

		byte[] data = new byte[]{1,2};
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

		System.out.println(inputStream);
		System.out.println(inputStream.read());
		System.out.println(inputStream.read());
		System.out.println(inputStream.read());
		System.out.println(inputStream.read());


	}
}
