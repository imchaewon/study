package com.example.java_.solid.dip.t1.준수하는설계;

public class CoffeeMachine implements CoffeeMaker{
    @Override
    public void brew() {
        System.out.println("커피를 내리는 로직");
    }
}