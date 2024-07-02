package com.example.java_.solid.isp.t1.위반하는설계;

public class MultiFunctionPrinter implements Machine{
    @Override
    public void print(Document document) {
        // 문서를 출력하는 로직
        System.out.println("문서를 출력하는 로직");
    }

    @Override
    public void scan(Document document) {
        // 문서를 스캔하는 로직
        System.out.println("문서를 스캔하는 로직");
    }

    @Override
    public void faxing(Document document) {
        // 문서를 팩싱하는 로직
        System.out.println("문서를 팩싱하는 로직");
    }
}