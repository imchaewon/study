package com.example.java_.z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class 걍테스트4 {
	public static void main(String[] args) throws IOException {
		new 걍테스트4().m1();
	}

	public void m1() {
		String s1 = "a";
		m2(s1);

		System.out.println("s1 = " + s1);
	}

	public void m2(String s1){
		s1 = "bbb";

		s1.equals(null);
	}
}