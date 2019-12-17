package com.NEU.laq.DesignPattern.DecoratorPattern;

public class Soy extends Decorator {
    public Soy(Drink component) {
        super(component);
        setDes("豆浆");
        setPrice(1.5f);
    }
}
