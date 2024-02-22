package com.example.java_.generic.t4;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {
		Juicer.makeJuice(new FruitBox<Fruit>());
//		Juicer.makeJuice(new FruitBox<Apple>()); //오류

	}
}

class Juicer{
	static Juice makeJuice(FruitBox<Fruit> box){ // static메소드에서는 타입 매개변수 T를 매개변수에 사용할 수 없으므로 아예 제네릭스를 적용하지 않던가, 타입 매개변수 대신 특정 타입을 지정해줘야한다
		String tmp = "";
		for(Fruit f : box.getList()) tmp += f + " ";
		return new Juice(tmp);
	}
//	static Juice makeJuice(FruitBox<Apple> box){ // 제네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않기때문에 메소드 중복 정의로 오류가 나게됨
//		String tmp = "";
//		for(Fruit f : box.getList()) tmp += f + " ";
//		return new Juice(tmp);
//	}
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