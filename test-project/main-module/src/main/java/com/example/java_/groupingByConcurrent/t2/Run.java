package com.example.java_.groupingByConcurrent.t2;

import java.util.List;

public class Run {
	public static void main(String[] args) {

		List<TestDto> testDtos = List.of(
				new TestDto("W", "Waiting"),
				new TestDto("R", "Running"),
				new TestDto("T", "Terminated")
		);



	}
}