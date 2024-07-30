package com.example.java_.solid.srp.t1.준수하는설계;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 사용자 정보만 관리하는 클래스
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String name;
    private String email;
}