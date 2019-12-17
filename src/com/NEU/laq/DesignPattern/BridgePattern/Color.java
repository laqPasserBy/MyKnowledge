package com.NEU.laq.DesignPattern.BridgePattern;

/**
 * 实现层接口
 */
public interface Color {
    void write(String penType, String content);
}

class Red implements Color{
    private String color = "红色";
    @Override
    public void write(String penType, String content) {
        System.out.println(color + "的" + penType + "写下了" + content);
    }
}

class White implements Color{
    private String color = "白色";
    @Override
    public void write(String penType, String content) {
        System.out.println(color + "的" + penType + "写下了" + content);
    }

}

class Black implements Color{
    private String color = "黑色";
    @Override
    public void write(String penType, String content) {
        System.out.println(color + "的" + penType + "写下了" + content);
    }

}
