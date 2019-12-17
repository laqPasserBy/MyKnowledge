package com.NEU.laq.DesignPattern.DecoratorPattern;

public class Chocolate extends Decorator {
    public Chocolate(Drink component) {
        super(component);
        setDes("巧克力");
        setPrice(2.5f);
    }
}
