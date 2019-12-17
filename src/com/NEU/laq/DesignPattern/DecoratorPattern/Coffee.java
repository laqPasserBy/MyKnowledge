package com.NEU.laq.DesignPattern.DecoratorPattern;

public class Coffee extends Drink{

    public Coffee() {
        setDes("Coffee");
        setPrice(0.0f);
    }

    @Override
    public float cost() {
        return getPrice();
    }

    @Override
    public String getDes() {
        return des + " " + getPrice();
    }


}
