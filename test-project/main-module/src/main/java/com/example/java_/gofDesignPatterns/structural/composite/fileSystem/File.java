package com.example.java_.gofDesignPatterns.structural.composite.fileSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File implements FileComponent {
	private String name;

	@Override
	public void show() {
		System.out.println(name);
	}
}