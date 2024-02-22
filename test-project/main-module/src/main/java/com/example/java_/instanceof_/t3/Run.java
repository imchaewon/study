package com.example.java_.instanceof_.t3;

public class Run {
	public static void main(String[] args) {

		Cat c1 = new Cat();
		Animal a1 = new Animal();
		Animal a2 = new Cat();

		System.out.println(c1 instanceof Cat);
		System.out.println(c1 instanceof Animal);
		System.out.println(a1 instanceof Cat);
		System.out.println(a1 instanceof Animal);
		System.out.println(a2 instanceof Cat);
		System.out.println(a2 instanceof Animal);


		Cat newC1 = null;
		Cat newC2 = null;

		if(a2 instanceof Cat){
			newC1 = (Cat) a2;
		}

//		System.out.println(a2.i1); // 업캐스팅이 됐기때문에 자식클래스의 멤버 사용 불가
		System.out.println(newC1.i1); // 다운캐스팅을 했기때문에 자식클래스의 멤버 사용 가능

		if(a1 instanceof Cat){
			newC2 = (Cat) a1; // 코드가 실행되지 않아 오류가 나지 않음
		}


	}
}
