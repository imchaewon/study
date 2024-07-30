package com.example.java_.solid.srp.t1.준수하는설계;

// 사용자 정보를 출력하는 클래스
public class UserPrinter {
    public void print(User user) {
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}