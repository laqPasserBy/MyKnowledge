package com.NEU.laq.DesignPattern.DecoratorPattern;

public abstract class Drink {
    protected String des;
    private float price;
    public abstract float cost();

    public abstract String getDes();

    public void setDes(String des) {
        this.des = des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
