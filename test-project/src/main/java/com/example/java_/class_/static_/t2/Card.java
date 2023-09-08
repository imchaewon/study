package com.example.java_.class_.static_.t2;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
public class Card {
	final int NUMBER;
	final String KIND;
	static int width = 100;
	static int height = 250;

	public Card(String kind, int number) {
		NUMBER = number;
		KIND = kind;
	}

	public Card() {
		this("HEART", 1);
	}
}

class Run{
	public static void main(String[] args) {

		Card c = new Card("HEART", 10);

//		c.NUMBER = 5; 컴파일오류

		System.out.println(c);

	}
}



























