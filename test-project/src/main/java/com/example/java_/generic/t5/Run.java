package com.example.java_.generic.t5;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		Juicer.makeJuice(new FruitBox<Fruit>());
		Juicer.makeJuice(new FruitBox<Apple>()); // 사용이 가능해지게됨

	}
}

class Juicer{
	static Juice makeJuice(FruitBox<? extends Fruit> box){ // 와일드 카드의 상한 제한. Fruit와 그 자손들만 가능.
//	static Juice makeJuice(FruitBox<? super Fruit> box){ // 와일드 카드의 하한 제한. Fruit와 그 조상들만 가능.
//	static Juice makeJuice(FruitBox<?> box){ // 그냥 ? 는 Object랑 똑같아서 의미가 없음 (이렇게 하면 형변환이 필요함)
		String tmp = "";
		for(Fruit f : box.getList()) tmp += f + " ";
		return new Juice(tmp);
	}
}

class FruitBox<T>{
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

class Juice{

	public Juice(String tmp) {
	}
}

class Fruit{}

class Apple extends Fruit{}
class Grape extends Fruit{}