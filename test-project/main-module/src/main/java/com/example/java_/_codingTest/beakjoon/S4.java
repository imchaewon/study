package com.example.java_._codingTest.beakjoon;

import java.util.Scanner;

public class S4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] s1 = sc.next().split("");
		int result = 0;
		for (int i = 0; i < s1.length; i++) {
			String s = s1[i].toLowerCase();
			switch (s){
				case "a", "b", "c":
					result += 3;
					break;
				case "d", "e", "f":
					result += 4;
					break;
				case "g", "h", "i":
					result += 5;
					break;
				case "j", "k", "l":
					result += 6;
					break;
				case "m", "n", "o":
					result += 7;
					break;
				case "p", "q", "r", "s":
					result += 8;
					break;
				case "t", "u", "v":
					result += 9;
					break;
				case "w", "x", "y", "z":
					result += 10;
					break;
			}
		}
		System.out.println(result);
	}
}