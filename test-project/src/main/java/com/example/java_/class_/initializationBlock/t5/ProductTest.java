package com.example.java_.class_.initializationBlock.t5;

public class ProductTest {
	static int i;
	int num;

	{
		i++;
		num = i;
	}

	void m1(){
	}

	public static void main(String[] args) {

		ProductTest p1 = new ProductTest();
		System.out.println(p1.num);

		ProductTest p2 = new ProductTest();
		System.out.println(p2.num);

	}

}
