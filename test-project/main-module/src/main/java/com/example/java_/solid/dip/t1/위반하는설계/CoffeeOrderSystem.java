package com.example.java_.solid.dip.t1.위반하는설계;

public class CoffeeOrderSystem {
    private CoffeeMachine coffeeMachine = new CoffeeMachine();

    void takeOrder() {
        System.out.println("주문을 받는 로직");
        coffeeMachine.brew();
    }
}