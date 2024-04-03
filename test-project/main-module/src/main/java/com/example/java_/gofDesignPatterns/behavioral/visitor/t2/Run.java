package com.example.java_.gofDesignPatterns.behavioral.visitor.t2;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Run {
	public static void main(String[] args) {
		Element book = new Book(10000);
		Element music = new Book(5000);
		Element movie = new Book(15000);

		Visitor visitor = new PriceCalculator();
		int totalPrice = book.accept(visitor) + music.accept(visitor) + movie.accept(visitor);
		System.out.println("totalPrice = " + totalPrice);
	}
}

interface Element{
	int accept(Visitor visitor);
}

@Getter
@AllArgsConstructor
class Book implements Element {
	private int price;

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}

@Getter
@AllArgsConstructor
class Music implements Element {
	private int price;

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}

@Getter
@AllArgsConstructor
class Movie implements Element {
	private int price;

	@Override
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}

interface Visitor{
	int visit(Book book);
	int visit(Music music);
	int visit(Movie movie);
}

class PriceCalculator implements Visitor{
	@Override
	public int visit(Book book) {
		return book.getPrice();
	}

	@Override
	public int visit(Music music) {
		return music.getPrice() * 2;
	}

	@Override
	public int visit(Movie movie) {
		return movie.getPrice() * 3;
	}
}


