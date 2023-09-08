package com.example.java_.httpRequest.t2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Run {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://ctgs.ktovisitkorea.com/api/visitkorea/lc01/7601e8b78aed45ec852059cc5b766947/api.do");
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF8"));

		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();

		System.out.println(sb);
	}
}
