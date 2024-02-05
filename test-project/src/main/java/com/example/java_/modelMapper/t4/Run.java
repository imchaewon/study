package com.example.java_.modelMapper.t4;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {
		ModelMapper modelMapper = new ModelMapper();

		C1 c1 = new C1("김김김", Arrays.asList(new C1.C3(1, "c3-1"), new C1.C3(2, "c3-2")));
		C2 c2 = modelMapper.map(c1, C2.class);

		List<C2.C4> c4s = c1.getC3s().stream()
				.map(e -> {
					C2.C4 c4 = modelMapper.map(e, C2.C4.class);
					c4.setIdid(e.getId());
					c4.setNamename(e.getName());

					return c4;
				})
				.collect(Collectors.toList());
		c2.setC4s(c4s);

		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);
	}

	@AllArgsConstructor
	@Getter
	@ToString
	private static class C1{
		String name;
		List<C3> c3s;

		@AllArgsConstructor
		@NoArgsConstructor
		@Getter
		@ToString
		private static class C3{
			int id;
			String name;
		}
	}

	@NoArgsConstructor
	@Setter
	@ToString
	private static class C2{
		String name;
		List<C4> c4s;

		@NoArgsConstructor
		@Setter
		@ToString
		private static class C4{
			int idid;
			String namename;
		}
	}
}