package com.example.java_.solid.srp.t1.위반하는설계;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class User {
    private String name;
    private String email;

    // Constructor, getters, and setters

    // 사용자 정보를 출력하는 메서드
    public void printUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }

    // 이메일 형식을 검증하는 메서드
    public boolean isValidEmail() {
        return email.contains("@");
    }
}