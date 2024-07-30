package com.example.java_.z.걍테스트;

public class C7 {
	public static void main(String[] args) {
        I1 i1 = new I1() {
            @Override
            public int calc(int i1, int i2) {
                return 0;
            }

            @Override
            public int add(int i1) {
                return 0;
            }
        };
	}

    public interface I1{
        int calc(int i1, int i2);
        int add(int i1);
    }
}