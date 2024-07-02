package com.example.java_.solid.isp.t1.위반하는설계;

public class OldFashionedPrinter implements Machine{
    @Override
    public void print(Document document) {
        // 문서를 출력하는 로직
        System.out.println("문서를 출력하는 로직");
    }

    @Override
    public void scan(Document document) {
        // 아무런 스캔 기능이 없음
    }

    @Override
    public void faxing(Document document) {
        // 아무런 팩싱 기능이 없음
    }
}