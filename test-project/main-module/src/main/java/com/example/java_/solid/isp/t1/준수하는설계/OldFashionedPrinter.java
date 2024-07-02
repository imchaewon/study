package com.example.java_.solid.isp.t1.준수하는설계;

public class OldFashionedPrinter implements Printer {
    @Override
    public void print(Document document) {
        // 문서를 출력하는 로직
        System.out.println("문서를 출력하는 로직");
    }
}