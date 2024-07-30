package com.example.java_.interface_.t15;

public interface Calculator {
    Calculator DEFAULT_CALCULATOR = (a, b) -> {
        return a + b + 100;
    };

    int calculate(int a, int b);
}