package com.NEU.laq.DesignPattern.BuilderPattern;

public class BuilderType1 {

    /**
     *  建造者模式, 也叫构建者模式.
     *  核心思想 : 将复杂对象的构建与表示相分离(将产品本身与产品的构建过程解耦)
     *  四个核心角色 :
     *      产品(product) : 最后要生成的对象类别
     *      抽象构建者(Builder) : 定义了构建产品各个部件的抽象方法, 以及组合(内嵌)了一个产品实例
     *      具体构建者(ConcreteBuilder) : 实现抽象构建者中的抽象方法, 在方法中完成对抽象构建者中产品实例的各个部件的操作(构建)
     *      指挥者(Director) : 聚合一个抽象构建者(Builder), 调用constructXxx方法指挥抽象构建者完成对象的构建并返回对象
     *  基本理念 : 逐步完成对一个复杂对象的构建(将组件按照某种顺序一个一个的进行构建)
     */
    public static void main(String[] args) {
        PersonBuilderDirector personBuilderDirector = new PersonBuilderDirector(new ThinPersonBuilder());
        Person person = personBuilderDirector.constructPerson();
        System.out.println(person);
        personBuilderDirector.setPersonBuiler(new FatPersonBuilder());
        person = personBuilderDirector.constructPerson();
        System.out.println(person);

    }

}


// Product
// 产品类权限应为public, 但为了方便在一个文件中实现, 所以设置为 default 权限
class Person{
    /**
     * 现认定Person类较为复杂, 构建其对象, 传入的参数较多, 此时选择将对象创建过程与对象表示相分离,
     * 即采用构建者模式, 同时对外提供空参构造器
     */
    private String head;
    private String hand;
    private String leg;


    /**
     * 权限为default, 导致此构造器只可在本包下使用, 也就是说, 包外的类无法直接new Person对象
     */
    Person(){

    }
    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    @Override
    public String toString() {
        return "Person{" +
                "head='" + head + '\'' +
                ", hand='" + hand + '\'' +
                ", leg='" + leg + '\'' +
                '}';
    }
}

// Builder
abstract class PersonBuilder{
    protected Person person = new Person();

    public abstract void createHead();

    public abstract void createHand();

    public abstract void createLeg();

    public Person getPerson(){
        return person;
    }

}

// ConcreteBuilder
class FatPersonBuilder extends PersonBuilder{

    @Override
    public void createHead() {
        person.setHead("Big head!");
    }

    @Override
    public void createHand() {
        person.setHand("Big hands!");
    }

    @Override
    public void createLeg() {
        person.setLeg("Big legs!");
    }
}

// ConcreteBuilder
class ThinPersonBuilder extends PersonBuilder{

    @Override
    public void createHead() {
        person.setHead("Small head!");
    }

    @Override
    public void createHand() {
        person.setHand("Small hands!");
    }

    @Override
    public void createLeg() {
        person.setLeg("Small legs!");
    }
}

// Director
class PersonBuilderDirector{
    PersonBuilder personBuiler = null;

    public PersonBuilderDirector(PersonBuilder personBuiler) {
        this.personBuiler = personBuiler;
    }

    public void setPersonBuiler(PersonBuilder personBuiler) {
        this.personBuiler = personBuiler;
    }

    public Person constructPerson(){
        // 逐步完成对象组件的创建以及组装
        personBuiler.createHead();
        personBuiler.createHand();
        personBuiler.createLeg();
        return personBuiler.getPerson();
        //return personBuiler.person;
    }
}
