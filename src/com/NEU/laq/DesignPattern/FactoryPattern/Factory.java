package com.NEU.laq.DesignPattern.FactoryPattern;


import java.util.Scanner;

public class Factory {
    /**
     *  工厂模式一共分为3种: 简单工厂模式, 工厂方法模式, 抽象工厂模式
     *  核心思想是: 将一个类的实例化过程提取出来, 由一个类同一管理
     *  基本形式: A.createXxx(args)
     *          根据形参决定创建的对象实例属于哪一个具体的子类
     *  三种方法的区别在于工厂的类型: 具体类; 抽象类; 接口
     *
     *  启示:
     *  1. 在对象中创建对象时, 使用工厂创建来代替new生成对象
     *  2. 尽量继承抽象类或者实现接口, 而不是继承具体的类
     *  3. 尽量不要重写父类已经实现的方法
     */
    public static void main(String[] args) {
        String type = null;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("请输入pizza类型:");
            type = sc.next();
            OrderPizza order = new OrderPizza(type) ;

        }while(true);

    }


}

// 第一种: 简单工厂模式, 也叫静态工厂模式  --> 工厂类是一个具体的类
class Pizza {
    protected String name;
    public Pizza() {

    }

    public static Pizza createPizza(String type){
        Pizza pizza = null;
        switch(type){
            case "cheese":
                pizza = new CheesePizze();
                break;
            case "greek":
                pizza = new GreekPizze();
                break;
            default :
                System.out.println("没有" + type + "类型的披萨");break;

        }
        // 如果输入的类型不对, 则返回空
        return pizza;
    }
    public void setName(String name){
        this.name = name;
    }
    public void prepare(){
        System.out.println(name + "正在准备材料");
    }
    public void bake(){
        System.out.println(name + "正在烘制");
    }
    public void cut(){
        System.out.println(name + "正在切割");
    }
    public void box(){
        System.out.println(name + "正在打包");
    }

}

class CheesePizze extends  Pizza{
    public CheesePizze(){
        super.setName("CheesePizza");
    }
}
class GreekPizze extends  Pizza{
    public GreekPizze(){
        super.setName("GreekPizza");
    }
}

// 第二种: 工厂方法模式  --> 工厂类是一个抽象类
abstract class Pizza2 {
    protected String name;
    public Pizza2() {

    }

    public static Pizza2 createPizza(String type){
        Pizza2 pizza = null;
        switch(type){
            case "cheese":
                pizza = new CheesePizze2();
                break;
            case "greek":
                pizza = new GreekPizze2();
                break;
            default :
                System.out.println("没有" + type + "类型的披萨");break;

        }
        // 如果输入的类型不对, 则返回空
        return pizza;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract void prepare();
    public void bake(){
        System.out.println(name + "正在烘制");
    }
    public void cut(){
        System.out.println(name + "正在切割");
    }
    public void box(){
        System.out.println(name + "正在打包");
    }

}

class CheesePizze2 extends  Pizza2{
    public CheesePizze2(){
        super.setName("CheesePizza");
    }

    @Override
    public void prepare() {
        System.out.println(getName() + "正在准备材料");
    }
}
class GreekPizze2 extends  Pizza2{
    public GreekPizze2(){
        super.setName("GreekPizza");
    }
    @Override
    public void prepare() {
        System.out.println(getName() + "正在准备材料");
    }
}

// 第三种: 抽象工厂模式  --> 工厂类是一个接口
// 当对象簇很大并且经常新增的时候使用这种方式
interface  Pizza3{
    Pizza3 createPizza();
    void  bake();
    default void welcome(){
        System.out.println("欢迎光临");
    }
}

class OrderPizza{
    public OrderPizza(String type){
        Pizza3 pizza = null;
        switch(type){
            case "cheese":
                pizza = new CheesePizze3();
                break;
            case "greek":
                pizza = new GreekPizze3();
                break;
                default:
                    System.out.println("没有" + type + "类型的pizza");
                    break;
        }
        if(pizza != null) {
            pizza.welcome();
            pizza.createPizza();
            pizza.bake();
        }
    }
}

class CheesePizze3 implements Pizza3{
    String name = "Cheese";

    public CheesePizze3(){
    }

    public String getName() {
        return name;
    }

    @Override
    public Pizza3 createPizza() {
        System.out.println(getName() + "Pizza正在制作");
        return new CheesePizze3();
    }

    @Override
    public void bake() {
        System.out.println(getName() + "Pizza正在烘制");
    }
}
class GreekPizze3 implements Pizza3{
    String name = "Greek";

    public String getName() {
        return name;
    }

    @Override
    public Pizza3 createPizza() {
        System.out.println(getName() + "Pizza正在制作");
        return new GreekPizze3();
    }
    @Override
    public void bake() {
        System.out.println(getName() + "Pizza正在烘制");
    }
}

