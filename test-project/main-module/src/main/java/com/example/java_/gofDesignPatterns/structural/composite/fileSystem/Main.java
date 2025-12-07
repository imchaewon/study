package com.example.java_.gofDesignPatterns.structural.composite.fileSystem;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// Leaf 객체 생성
		File file1 = new File();
		file1.setName("file1.txt");

		File file2 = new File();
		file2.setName("file2.txt");

		// Composite 객체 생성
		Directory dir1 = new Directory();
		dir1.getChildren().add(file1);
		dir1.getChildren().add(file2);

		File file3 = new File();
		file3.setName("file3.txt");

		// 상위 Composite
		Directory root = new Directory();
		root.setChildren(new ArrayList<>());
		root.getChildren().add(dir1);
		root.getChildren().add(file3);

		// 전체 트리 출력
		root.show();
	}
}