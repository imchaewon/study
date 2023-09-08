package com.example.java_.stream.initial.arrayToStream.t1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Run {
	public static void main(String[] args) {

		int[] intArr = {1,2,3,4,5};
		String[] StringArr = {"a","b","c"};
		List<Integer> list = Arrays.asList(1,2,3,4,5);

		IntStream intStream = Arrays.stream(intArr);
		IntStream intStream2 = Arrays.stream(intArr);
		IntStream intStream3 = Arrays.stream(intArr,1,3);
		Stream<Integer> stream = Stream.of(1,2,3,4,5);
		Stream<Integer> stream2 = list.stream();
		Stream<String> stream3 = Arrays.stream(StringArr);
		Stream<String> stream4 = Stream.of(StringArr);

//		intStream.forEach(System.out::println);
		intStream.forEach(i -> {
			if(i == 2){
				return;
			}
			System.out.println(i);
		});
		System.out.println(intStream2.boxed().collect(Collectors.toList()));
		System.out.println(intStream3.boxed().collect(Collectors.toList()));
		System.out.println(stream.collect(Collectors.toList()));
		System.out.println(stream2.collect(Collectors.toList()));
		System.out.println(stream3.collect(Collectors.toList()));
		System.out.println(stream4.collect(Collectors.toList()));

		System.out.println(Arrays.stream(intArr).boxed().map(i->i+"").collect(Collectors.joining()));
		System.out.println(Arrays.stream(intArr).boxed().map(i->i+"").collect(Collectors.joining("_")));

		System.out.println("-----");

		list.stream().reduce((x,y) -> x + y).ifPresent(System.out::println);
//		list.stream().reduce((x,y) -> x + y).orElse(100);
		Arrays.stream(intArr).boxed().reduce((x,y) -> x + y).ifPresent(System.out::println);
		Arrays.stream(intArr).boxed().map(i->i+"").reduce((x,y) -> x + y).ifPresent(System.out::println);



	}
}
