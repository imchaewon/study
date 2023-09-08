package com.example.java_.inputStream.bufferedReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {
	public static void main(String[] args) throws IOException {
		// BufferedReader를 생성하고 표준 입력으로부터 데이터를 읽어옴
		InputStreamReader in = new InputStreamReader(System.in);
		System.out.println("in: " + in);
		BufferedReader br = new BufferedReader(in);
		System.out.println("br: " + br);

		// 한 줄의 텍스트를 읽어옴
		String inputLine = br.readLine();
		String inputLine2 = br.readLine();

		System.out.println("입력한 내용: " + inputLine);
		System.out.println("입력한 내용2: " + inputLine2);

		// BufferedReader 사용 후에는 close() 메서드를 호출하여 자원을 정리해야함
		br.close();

	}
}
