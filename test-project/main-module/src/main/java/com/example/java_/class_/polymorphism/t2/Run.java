package com.example.java_.class_.polymorphism.t2;

import lombok.ToString;

public class Run {
	public static void main(String[] args) {
		Tv t = new Tv();

		Buyer b = new Buyer();
		b.buy(t);
		System.out.println(b);
	}
}

@ToString
class Product{
	int price; // 제품의 가격
	int bonusPoint; // 제품구매 시 제공하는 보너스점수

	public Product(int price) {
		this.price = price;
	}
}
class Tv extends Product {
	public Tv() {
		super(500);
	}
}
class Computer extends Product {
	public Computer() {
		super(300);
	}
}
class Audio extends Product {
	public Audio() {
		super(100);
	}
}
@ToString
class Buyer{ // 고객, 물건을 사는 사람
	int money = 1000; // 소유금액
	int bonusPoint = 0; // 보너스점수

//	void buy(Tv t){ // buy(Tv t) 로는 Tv밖에 살 수 없기 때문에 다른 제품들도 구입할 수 있는 메서드가 추가로 필요함
//		money = money - t.price; // Buyer가 가진 돈(money)에서 제품의 가격(t.price)만큼 뺀다.
//		bonusPoint = bonusPoint + t.bonusPoint; // Buyer의 보너스점수(bonusPoint)에 제품의 보너스점수(t.bonusPoint)를 더한다.
//	}
//	void buy(Computer c){
//		money = money - c.price;
//		bonusPoint = bonusPoint + c.bonusPoint;
//	}
//	void buy(Audio a){
//		money = money - a.price;
//		bonusPoint = bonusPoint + a.bonusPoint;
//	}
	void buy(Product p){ // 이 메소드 하나로 위의 3개 메소드를 모두 커버 가능함
		money = money - p.price;
		bonusPoint = bonusPoint + p.bonusPoint;
	}
}







