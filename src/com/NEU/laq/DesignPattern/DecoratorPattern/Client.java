package com.NEU.laq.DesignPattern.DecoratorPattern;

/**
 * 装饰者模式：
 *      被装饰者（Component）：最基本、最核心、最抽象的抽象基类，定义了整个设计体系的功能
 *      具体的被装饰者（ConcreteComponent）：继承了Component并实现了其中的抽象方法，是真正被装饰的对象
 *      装饰者（Decorator）：可以追加到被装饰者对象上的责任（功能）的基类，也可充当被装饰者（此时的装饰者内部一定存在一个被装饰者对象）
 *      具体装饰者（ConcreteDecorator）：可以追加到被装饰者对象上的具体某一项责任（功能）
 * 基本目的：
 *      做为继承关系的一种可替代方法，装饰者模式可以动态的为对象附加责任
 * 核心思想：
 *      在被装饰者的行为之前 或/与 之后，加上自己的行为，以达到特定的目的
 *          -->  在不改变原来数据的基础上，加上属于自己的特征(用自己的特征进行包装）
 *          -->  二者的行为是同一个行为的不同实现（同名方法），这使得这种方式看起来像是递归调用
 * 基本实现：
 *      让装饰者基类（Decorator） 继承 + 组合 被装饰者类（Component），这样参与表层操作的只有Decorator和Component，
 *      很好地进行了解耦，使得二者可以分别单独扩展（添加更多的子类）。
 * 具体表现：
 *      在被装饰者外层 “包裹” 一层装饰者，然后再把新的装饰者当做被装饰者进行进一步“包裹”。
 *          -->  裹上面包糠，裹上鸡蛋液。。。。
 * 【注】 装饰器模式中所有的类都是同源的，即有一个共同的祖先
 */
public class Client {
    public static void main(String[] args) {
        Drink drink = new LongBlack();
        System.out.println("描述：" + drink.getDes());
        System.out.println("花费：" + drink.getPrice());
        // 装饰一份牛奶
        drink = new Milk(drink);
        System.out.println("装饰一份牛奶 描述：" + drink.getDes());
        System.out.println("装饰一份牛奶 花费：" + drink.cost());
        // 装饰一份豆浆
        drink = new Soy(drink);
        System.out.println("装饰一份豆浆 装饰一份牛奶 描述：" + drink.getDes());
        System.out.println("装饰一份豆浆 装饰一份牛奶 花费：" + drink.cost());
        // 装饰一份牛奶
        drink = new Milk(drink);
        System.out.println("装饰一份牛奶 装饰一份豆浆 装饰一份牛奶 描述：" + drink.getDes());
        System.out.println("装饰一份牛奶 装饰一份豆浆 装饰一份牛奶 花费：" + drink.cost());

        Drink drink2 = new Espresso();
        drink2 = new Chocolate(new Soy(new Milk(drink2)));
        System.out.println("装饰一份巧克力 装饰一份豆浆 装饰一份牛奶 描述：" + drink2.getDes());
        System.out.println("装饰一份巧克力 装饰一份豆浆 装饰一份牛奶 费用：" + drink2.cost());

    }
}
