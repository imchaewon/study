package com.example.java_.class_.initializationBlock.t3;

class BlockTest{
	public static void main(String[] args) {
		System.out.println(111);
		BlockTest bt = new BlockTest();
		System.out.println(222);
		BlockTest bt2 = new BlockTest();
	}
	static{
		// 클래스 초기화 블럭
		System.out.println("static{}");
	}
	{
		// 인스턴스 초기화 블럭
		System.out.println("{}");
	}

	public BlockTest(){
		System.out.println("생성자");
	}
}