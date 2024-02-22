package com.example.java_.modelMapper.t3;

import lombok.*;
import org.modelmapper.ModelMapper;

public class Run {
	public static void main(String[] args) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true); // null일 경우 매핑 안하는 설정

		mapper.typeMap(C1.class, C2.class).addMappings(mapping -> {
			mapping.map(C1::getName, C2::setNamee);
		});

		C1 c1 = new C1(1, "김김김", null);
		C2 c2 = mapper.map(c1, C2.class);

		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);
	}

	@AllArgsConstructor
	@Getter
	@ToString
	private static class C1{
		int id;
		String name;
		Integer ddd;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@ToString
	private static class C2{
		int id;
		String namee;
		Integer ddd = 123;
	}
}