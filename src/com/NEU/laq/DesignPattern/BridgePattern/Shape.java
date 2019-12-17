package com.NEU.laq.DesignPattern.BridgePattern;

public abstract class Shape {
    protected Color2 color;

    public Shape(Color2 color) {
        this.color = color;
    }
    public abstract void create();
}

class Circle extends Shape{
    private String shape = "圆形";

    public Circle(Color2 color){
        super(color);
    }

    @Override
    public void create() {
        color.create(shape);
    }

}

class Triangle extends Shape{
    private String shape = "三角形";

    public Triangle(Color2 color){
        super(color);
    }

    @Override
    public void create() {
        color.create(shape);
    }

}

class Square extends Shape{
    private String shape = "正方形";

    public Square(Color2 color){
        super(color);
    }

    @Override
    public void create() {
        color.create(shape);
    }

}
