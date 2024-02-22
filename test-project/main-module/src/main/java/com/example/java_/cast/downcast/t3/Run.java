package com.example.java_.cast.downcast.t3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		ArrayList<Integer> list = (ArrayList<Integer>) Arrays.asList(1,2,3);

		LinkedList<Integer> list2 = (LinkedList) Arrays.asList(1,2,3);
		list2.removeFirst();

		List<Integer> list3 = Arrays.asList(1,2,3);
//		list3.removeFirst(); // 부모객체가 자식클래스를 쓰는것은 불가능
		((LinkedList<Integer>)list3).removeFirst(); // 자식객체로 다운캐스팅 하면 쓸 수 있음

//		ArrayList<Integer> list2 = (LinkedList<Integer>) Arrays.asList(1,2,3); // 형제 클래스끼리는 아예 타입이 다르기 때문에 참조 형변환이 불가능함
//		개와 고양이는 모두 동물에 속해있지만 개에서 고양이, 고양이에서 개로는 바꿀 수 없음
//		개에서 동물로 가는것을 업캐스팅, 동물에서 개로 가는것을 다운캐스팅이라고 함

	}
}
