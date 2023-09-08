package com.example.java_.array.sort;

import java.util.Arrays;
import java.util.Comparator;

public class Run {
	public static void main(String[] args) {
		String[] arr = {"cat", "Dog", "lion", "tiger"};

		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 안함
		System.out.println(Arrays.toString(arr));

		Arrays.sort(arr, new Descending()); // 역순 정렬
		System.out.println(Arrays.toString(arr));

	}
}

class Descending implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable){
			Comparable c1 = (Comparable) o1;
			Comparable c2 = (Comparable) o2;
			System.out.println("c1.compareTo(c2): "+c1.compareTo(c2));
			return c1.compareTo(c2) * -1;
		}
		return -1;
	}
}