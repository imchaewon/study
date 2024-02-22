package com.example.java_.generic.t2;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
//		Box<Apple> box = new Box<Grape>(); // 에러
//		Box<Fruit> box = new Box<Apple>(); // 에러
		Box<Apple> box = new FruitBox<Apple>(); // OK
		Box<Apple> box2 = new Box<>(); // 추정이 가능한경우 생략 가능
		box.add(new Apple()); // OK
//		box.add(new Grape()); // 에러

		Box<Fruit> box3 = new Box<>(); // 타입 T를 Fruit으로 할경우
		box3.add(new Apple()); // Fruit의 자손은 가능

		
	}
}

class Box<T>{
	List<T> list = new ArrayList<>();

	void add(T item){
		list.add(item);
	}
	T get(int i){
		return list.get(i);
	}
	List<T> getList(){
		return list;
	}
	int size(){
		return list.size();
	}
	public String toString(){
		return list.toString();
	}
}

class FruitBox<T> extends Box{
	void m1(T p){
	}
}

class Apple extends Fruit{

}

class Grape extends Fruit{

}

class Fruit{

}