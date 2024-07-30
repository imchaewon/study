package com.example.java_.solid.dip.t1.준수하는설계;

public class CoffeeOrderSystem {
    private CoffeeMaker coffeeMachine = new CoffeeMachine();

    void takeOrder() {
        System.out.println("주문을 받는 로직");
        coffeeMachine.brew();
    }
}