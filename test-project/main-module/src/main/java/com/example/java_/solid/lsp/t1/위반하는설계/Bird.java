package com.example.java_.solid.lsp.t1.위반하는설계;

public class Bird extends Animal { // 새는 소리를 내는 방식이 다르기 때문에 "동물이 소리를 낸다"가 될 수 없음. makeSound 메서드를 오버라이드하면서 LSP를 위반함
    @Override
    void makeSound(){
        System.out.println("새가 지저귄다.");
    }
}