package com.NEU.laq.DesignPattern.AdapterPattern;


public class DefaultAdapter {
    /**
     * 默认适配器模式:  --> 也叫缺省适配器模式
     *      使用一个类/抽象类为一个接口或者抽象类中的所有抽象方法提供默认实现
     *          --> 因为现在接口允许提供默认方法, 所以这种模式基本已经不会使用
     * 使用方法:
     *      在使用时, 通过创建匿名实现类, 并在其中重写所需方法
     * 使用场景:
     *      当不想使用一个接口的全部方法时   --> 只使用接口的部分方法
     */
    public static void main(String[] args) {
        MyInterface myInterface = new MyClass(){
            @Override
            public void method1() {
                System.out.println("method1 被调用了");
            }

            @Override
            public void method2() {
                System.out.println("method2 被调用了");
            }

            @Override
            public void method3() {
                System.out.println("method3 被调用了");
            }
        };
        // 调用已经被重写的方法
        myInterface.method1();
        myInterface.method2();
        myInterface.method3();
}

}

interface MyInterface{
    void method1();
    void method2();
    void method3();
    void method4();
    void method5();
}

abstract class MyClass implements MyInterface{

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }

    @Override
    public void method4() {

    }

    @Override
    public void method5() {

    }
}
