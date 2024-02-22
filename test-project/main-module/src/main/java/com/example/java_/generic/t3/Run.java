package com.example.java_.generic.t3;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
//		FruitBox<Toy> fruitBox = new FruitBox<Toy>(); 에러. Fruit의 자손클래스로만 타입지정 가능
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // OK
		FruitBox<Apple> appleBox = new FruitBox<>(); // OK

		fruitBox.add(new Apple()); // OK. Apple가 Fruit의 자손
		fruitBox.add(new Grape()); // OK. Grape가 Fruit의 자손

	}
}

class FruitBox<T extends Fruit & Eatable>{
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

class Fruit implements Eatable {}
class Apple extends Fruit{}
class Grape extends Fruit{}
class Toy{}
interface Eatable{};

