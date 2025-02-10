package com.example.java_._codingTest;

public class 걍테스트7 {
    public static void main(String[] args) {
        C2 c2 = new C2();
        c2.id = 1L;
        c2.name = "김준수";
        System.out.println(c2);

        C1 c1 = c2;
        System.out.println("c1 = " + c1);
    }

    static class C2 extends C1{
        public String name;

        @Override
        public String toString() {
            return "C2{id=" + id + ", name='" + name + "'}";
        }
    }

    static class C1{
        public Long id;
    }
}