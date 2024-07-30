package com.example.java_.z;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class 걍테스트6 {
    public static void main(String[] args) {

        Map<String,Object> map = new ConcurrentHashMap<>();

        map.put("a", "aaa");

        map.remove("a");
        map.remove("b");

        System.out.println("map = " + map);

    }
}