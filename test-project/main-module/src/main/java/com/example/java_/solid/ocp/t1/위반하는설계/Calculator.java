package com.example.java_.solid.ocp.t1.위반하는설계;

public class Calculator {
    double calculatorCircleArea(Circle circle) {
        // 원의 면적 계산 로직
        return 123;
    }

    double calculatorRectangleArea(Rectangle rectangle) {
        // 사각형의 면적 계산 로직
        return 123;
    }

    double calculatorTriangleArea(Triangle triangle) {
        // 삼각형의 면적 계산 로직
        return 123;
    }

    // 새로운 도형 '타원'을 추가하려면 Calculator 클래스를 수정해야 함
}