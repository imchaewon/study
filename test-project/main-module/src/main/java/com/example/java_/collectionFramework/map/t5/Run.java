package com.example.java_.collectionFramework.map.t5;

import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        Map<String, Person> map = new HashMap<>();
        map.put("cherry", new Person("original cherry", 33));
        map.computeIfAbsent("apple", key -> new Person("ap", 11));
        map.computeIfAbsent("banana", key -> new Person(key, 22));
        map.computeIfAbsent("cherry", key -> new Person(key, 444));
        System.out.println("map = " + map);
    }

    public static class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}