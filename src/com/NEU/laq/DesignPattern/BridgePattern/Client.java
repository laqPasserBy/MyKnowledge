package com.NEU.laq.DesignPattern.BridgePattern;

/**
 * 桥接模式：
 *      核心思想：将抽象层与实现层分离，使二者可以独立变化。
 *      具体实现方式：将实现层与抽象层的继承关系，转换为抽象层依赖实现层的关联关系（解耦）
 *      核心角色：
 *          抽象类（Abstraction）：抽象层的交互者，充当桥头
 *          扩充抽象类（RefinedAbstraction）：带有具体实现细节的抽象层实体
 *          实现类接口（Implementor）：实现层的交互者，充当桥尾
 *          具体实现类（ConcreteImplementor）：所需功能真正的实现者
 *      工作原理：
 *          通过桥接类完抽象化角色和具体化角色的动态耦合 --> 所需的实体类是在运行时完成架构的
 *      源码案例：
 *          JVM协调操作系统和Java应用程序： JVM是桥，操作系统是抽象层，具体Java程序是实现层
 *          JDBC驱动程序： JDBC驱动程序是桥，所在的应用系统是抽象化角色（运行在其中的Java程序是扩展的抽象类），而每一个具体的数据库是实现化角色
 *      优点：
 *          实现和抽象分离，二者可以互不影响的独立改变；当需要扩展时，只需要单独修改一个维度即可，无需修改整个程序(类的数量越多,效果越明显)
 *      缺点：
 *          必须得找到一个有两个独立变化维度的类，并且需要完成这两个维度的划分，才可以完成桥接模式的实现，难度较大
 *      【注】桥接模式的表现形式为：化乘法为加法
 *              第一个维度需要a个类，第二个维度需要b个类：
 *                  此时，若采用传统的设计方法，需要设计 a * b 个类，并且通过继承方式实现，扩展时较繁琐
 *                  采用桥接模式后，只需要设计 a + b 个类，即可达到需求（运行时通过动态耦合生成所需目标），并且类与类之间的关系大多数由继承转变为了关联，易于扩展和维护
 */
public class Client {

    /**
     * 案列描述：
     *      三种型号的毛笔（抽象层）：小，中，大
     *      三种颜色（实现层）：红，白，黑
     */
    public static void main(String[] args) {

//        Pen pen = new SmallPen(new Red());
//        pen.write("嘿嘿嘿");
//        pen = new MiddlePen(new White());
//        pen.write("哈哈哈");
//        pen = new MiddlePen(new Black());
//        pen.write("哦哦哦");
//        pen = new MiddlePen(new Red());
//        pen.write("no no no");
//        pen = new BigPen(new White());
//        pen.write("nice nice nice");
//        pen = new BigPen(new Black());
//        pen.write("奥利给");

        Shape shape = new Circle(new Red2());
        shape.create();
        shape = new Triangle(new Red2());
        shape.create();
        shape = new Square(new Red2());
        shape.create();
        shape = new Circle(new Balck2());
        shape.create();
        shape = new Triangle(new Balck2());
        shape.create();
        shape = new Square(new Balck2());
        shape.create();
        shape = new Circle(new White2());
        shape.create();
        shape = new Triangle(new White2());
        shape.create();
        shape = new Square(new White2());
        shape.create();



    }
}
