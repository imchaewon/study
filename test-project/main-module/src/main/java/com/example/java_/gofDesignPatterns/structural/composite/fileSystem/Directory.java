package com.example.java_.gofDesignPatterns.structural.composite.fileSystem;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Directory implements FileComponent{
	private List<FileComponent> children = new ArrayList<>();

	@Override
	public void show() {
		for (FileComponent c : children) c.show();
	}
}