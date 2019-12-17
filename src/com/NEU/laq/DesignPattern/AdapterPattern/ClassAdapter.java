package com.NEU.laq.DesignPattern.AdapterPattern;


/**
 * 适配器模式:
 *      目的:让两个本不兼容的类或者接口可以一起工作
 *             --> 将一个方法的签名改变为另一个形式
 *                  --> 用于在被适配者的源码未知不易修改等情况下的接口不匹配问题
 *      主要角色:
 *          1. 目标接口/类: 要转换成的接口或类   -->  其中包含了我们所需要的方法
 *          2. 适配者: 需要被适配的类 --> 其中包含了我们需要使用的功能, 但是签名不匹配
 *          3. 适配器: 完成所需功能的适配  --> 将一个功能的签名包装成所需的签名
 *
 *          --> 一般情况下, 每一个目标接口, 对应着一个完成适配的方法(函数式接口, 这种情况下, 客户端只需要与该接口交互即可, 同时满足了最少知道原则),
 *              由相应的适配器实现该接口, 并完成适配功能
 *       解耦:
 *            让客户端与具体使用的适配器解耦, 通过接口/抽象类交互
 *
 */
public class ClassAdapter {
    /**
     * 类适配器模式:
     *      适配器以继承的的方式完成, 以类的形式代表被适配者
     * 实现方式:
     *      继承被适配者, 实现包含目标功能的目标接口
     * 优点:
     *      继承使得重写被适配者中的功能方法变得容易
     * 缺点:
     *      1. java是单继承, 所以每个适配器只能适配一个被适配者, 且目标功能只能存放在一个接口中
     *      2. 因为继承是高耦合, 所以被适配者和适配器之间的解耦并不明显
     */
    public static void main(String[] args) {
        Output5V output5V = new Adapter5V();
        int voltage = output5V.output5V();
        System.out.println(voltage + "V");
    }

}

class Adaptee220V{
    private final int output = 220;

    public int output220V() {
        System.out.println("获取到了" + output + "V的电压");
        return output;
    }

}
interface Output5V{
    default int output5V(Adapter adapter){
        return -1;
    }
    default int output5V(){
        return -1;
    }
}
class Adapter5V extends Adaptee220V implements Output5V{
    private final int input = 220;
    private int output = 0;


    @Override
    public int output5V() {
        int input = output220V();
        if(this.input != input)
            throw new RuntimeException("适配器不合适! 请更换适配器");
        output = input / 44;
        System.out.println("获取到" + output + "V的电压");
        return output;
    }
}

