package com.NEU.laq.DesignPattern.AdapterPattern;

import java.util.ArrayList;
import java.util.List;

public class ObjectAdapter {
    /**
     * 对象适配器:
     *      适配器以聚合 / 组合的方式与被适配者关联   -->  被适配者以成员变量(属性)的形式出现在适配器中
     *
     * 实现方式:
     *      适配器类中包含被适配者作为成员变量,并实现包含所需功能的接口
     *
     * 优点:
     *      1. 一个适配器可以同时将多个被适配者适配到同一目标
     *      2. 对适配器和被适配者二次解耦 (取消继承)
     *      3. 适配器可以对 以被适配者为基类的对象簇 完成适配  --> 适配器不仅可以适配被适配者, 还可以适配其子类(里氏替换原则)
     *
     * 缺点:
     *      修改被适配者中的功能方法时, 会比较麻烦
     */
    public static void main(String[] args) {
        List<Adapter> list = new ArrayList<Adapter>(){
            {
                // 静态代码块完成初始化
                add(new JapanAdapter());
                add(new ChinaAdapter());

            }
        };
//        List<Adapter> list = new ArrayList<>();
//        list.add(new JapanAdapter());
//        list.add(new ChinaAdapter());
        NormalVoltage normalVoltage = new ChinaVoltage();
        Adapter adapter = null;
        for(Adapter tempAdapter : list){
            if(tempAdapter.support(normalVoltage)){
                adapter = tempAdapter;
                break;
            }
        }
        if(adapter == null)
            throw new RuntimeException("找不到合适的适配器!");
        int voltage = adapter.output5V(normalVoltage);
        System.out.println(voltage);


        normalVoltage = new JapanVoltage();
        adapter = null;
        for(Adapter tempAdapter : list){
            if(tempAdapter.support(normalVoltage)){
                adapter = tempAdapter;
                break;
            }
        }
        if(adapter == null)
            throw new RuntimeException("找不到合适的适配器!");
        voltage = adapter.output5V(normalVoltage);
        System.out.println(voltage);

    }


}

interface NormalVoltage{
    int getVoltage();
}

class ChinaVoltage implements NormalVoltage{
    private final int voltage = 220;
    public int getVoltage(){
        System.out.println("获取到中国的" + voltage + "V电压");
        return voltage;
    }
}

class JapanVoltage implements NormalVoltage{
    private int voltage = 110;

    @Override
    public int getVoltage() {
        System.out.println("获取到日本的" + voltage + "V电压");
        return voltage;
    }
}

interface Adapter {
    boolean support(NormalVoltage normalVoltage);
    int output5V(NormalVoltage normalVoltage);
}

class ChinaAdapter implements Adapter{
    private final int adapterVoltage = 220;

    public boolean support(NormalVoltage normalVoltage){
        return normalVoltage.getVoltage() == this.adapterVoltage;
    }

    public int output5V(NormalVoltage normalVoltage) {
        //int curVoltage = ((ChinaAdapter)adapter).getVoltage();
        int curVoltage = normalVoltage.getVoltage();
        int res = curVoltage / 44;
        System.out.println("获得了" + res + "V的电压");
        return  res;
    }

}
class JapanAdapter implements Adapter{
    private final int adapterVoltage = 110;

    public boolean support(NormalVoltage normalVoltage){
        return normalVoltage.getVoltage() == this.adapterVoltage;
    }

    public int output5V(NormalVoltage normalVoltage) {
        // int curVoltage = ((ChinaAdapter)adapter).getVoltage();
        int curVoltage = normalVoltage.getVoltage();
        int res = curVoltage / 22;
        System.out.println("获得了" + res + "V的电压");
        return  res;
    }


}
