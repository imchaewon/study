package com.example.java_.enum_.t1;

public class Run {
	public static void main(String[] args) {
		System.out.println(Card.Kind.CLOVER == Card.Kind.HEART);
	}
}

class Card{
	enum Kind{CLOVER, HEART, DIAMOND, SPADE}
	enum Value{TWO, THREE, FOUR}
}