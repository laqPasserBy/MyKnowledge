package com.NEU.laq.DesignPattern.BridgePattern;

public interface Color2 {
    void create(String shape);
}

class Red2 implements Color2{
    private String color = "红色";
    @Override
    public void create(String shape) {
        System.out.println(color + "的" + shape + "被创建了");
    }
}

class Balck2 implements Color2{
    private String color = "黑色";
    @Override
    public void create(String shape) {
        System.out.println(color + "的" + shape + "被创建了");
    }
}

class White2 implements Color2{
    private String color = "白色";
    @Override
    public void create(String shape) {
        System.out.println(color + "的" + shape + "被创建了");
    }
}
