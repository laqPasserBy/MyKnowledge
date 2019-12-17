package com.NEU.laq.DesignPattern.DecoratorPattern;

public class Milk extends Decorator {
    public Milk(Drink component) {
        super(component);
        setDes("牛奶");
        setPrice(2.0f);
    }
}
