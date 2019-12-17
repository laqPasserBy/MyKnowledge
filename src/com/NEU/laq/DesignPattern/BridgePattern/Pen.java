package com.NEU.laq.DesignPattern.BridgePattern;

/**
 * 抽象层抽象类
 */
public abstract class Pen {
    protected Color color ;

    public Pen(Color color){
        this.color = color;
    }

    public abstract void write(String str);
}

class SmallPen extends Pen{
    private String penType = "小型笔";
    public SmallPen(Color color){
        super(color);
    }

    @Override
    public void write(String str) {
        color.write(penType,str);
    }
}

class MiddlePen extends Pen{
    private String penType = "中型笔";
    public MiddlePen(Color color){
        super(color);
    }

    @Override
    public void write(String str) {
        color.write(penType,str);
    }
}

class BigPen extends Pen{
    private String penType = "大型笔";
    public BigPen(Color color){
        super(color);
    }

    @Override
    public void write(String str) {
        color.write(penType,str);
    }
}