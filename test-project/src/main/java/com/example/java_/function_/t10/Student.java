package com.example.java_.function_.t10;

public class Student {
	private String name;
	private int mathScore;
	private int englishScore;

	public Student(String name, int mathScore, int englishScore) {
		this.name = name;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
	}

	public String getName() {
		return name;
	}

	public int getMathScore() {
		return mathScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}
}
