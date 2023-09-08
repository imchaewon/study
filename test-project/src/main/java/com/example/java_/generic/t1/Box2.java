package com.example.java_.generic.t1;

public class Box2<T> { // T를 타입변수 라고 함. Type의 첫글자에서 따온것
	T item;
	void setItem(T item){
		this.item = item;
	}
	T getItem() {
		return item;
	}

//	static T item2; // 에러
//	static T getItem2(){return item;} // 에러
}
