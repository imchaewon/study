package com.example.java_.comparator.t1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		List<Player> players = new ArrayList<>();

		players.add(new Player("Alice", 899));
		players.add(new Player("Bob", 982));
		players.add(new Player("Chloe", 1090));
		players.add(new Player("Dale", 982));
		players.add(new Player("Eric", 1018));

		System.out.println(players);

		players.sort(Player::compareTo);

		System.out.println(players);

	}

	@AllArgsConstructor
	@ToString
	@Getter
	static class Player implements Comparable<Player>{

		private String name;
		private int score;

		@Override
		public int compareTo(Player o) {
			return o.getScore() - getScore();
		}
	}
}
