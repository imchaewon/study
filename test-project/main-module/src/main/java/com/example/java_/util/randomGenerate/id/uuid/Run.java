package com.example.java_.util.randomGenerate.id.uuid;

import java.util.UUID;

public class Run {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid);
		}
	}
}