package com.example.java_.solid.srp.t1.준수하는설계;

// 이메일 형식을 검증하는 클래스
public class EmailValidator {
    public boolean isValid(String email) {
        return email.contains("@");
    }
}