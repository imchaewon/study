package com.example.java_.generic.t6;

import java.util.*;

public class Run {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return 0;
			}
		});
	}

//	static <T> void sort(List<T> list, Comparator<? super T> c){
//	}

	static <T> void sort(List<T> list, Comparator<T> c){
	}
}
