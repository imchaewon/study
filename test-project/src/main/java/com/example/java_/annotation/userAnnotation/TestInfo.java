package com.example.java_.annotation.userAnnotation;

public @interface TestInfo {
	int count() default 1; // 기본값을 1로 지정
	String testedBy();
	String[] testTools();
	TestType testType(); // enum 포함 가능
	DateTime testDate(); // 다른 어노테이션 포함 가능
}

