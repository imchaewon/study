package com.example.java_.class_.variable.t1;

public class Run {
	public static void main(String[] args) {

		Card c1 = new Card();
		c1.kind = "heart";
		c1.num = 3;

		Card c2 = new Card();
		c2.kind = "diamond";

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1.width);
		c2.width = 300;
		c2.height = 500;
		System.out.println(c1.width);
		System.out.println(Card.width);

	}
}
