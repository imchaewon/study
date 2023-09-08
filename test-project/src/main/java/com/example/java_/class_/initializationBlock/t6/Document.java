package com.example.java_.class_.initializationBlock.t6;

public class Document {
	static int i;
	String name;

	public Document(){
		this("제목없음" + ++i);
	}

	public Document(String name){
		this.name = name;
		System.out.printf("문서 %s가 생성되었습니다\r\n", this.name);
	}

	public static void main(String[] args) {

		Document d1 = new Document();
		Document d2 = new Document("품의서");
		Document d3 = new Document();
		Document d4 = new Document();

	}

}
