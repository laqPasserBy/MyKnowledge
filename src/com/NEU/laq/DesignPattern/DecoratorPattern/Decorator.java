package com.NEU.laq.DesignPattern.DecoratorPattern;

// 装饰器
public abstract class Decorator extends Drink {
    private Drink component;

    public Decorator(Drink component) {
        this.component = component;
    }

    @Override
    public float cost() {
        return getPrice() + component.cost();
    }

    public String getDes(){
        return des + " " + getPrice() + " && " + component.getDes();
    }
}
