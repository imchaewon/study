package com.example.java_.interface_.t15;

public class Run {
    public static void main(String[] args) {
        Calculator sum = (a, b) -> a + b;
        int result = sum.calculate(1, 2);
        System.out.println("result = " + result);

        Calculator defaultCalc = Calculator.DEFAULT_CALCULATOR;
        int result2 = defaultCalc.calculate(1, 2);
        System.out.println("result2 = " + result2);
    }
}