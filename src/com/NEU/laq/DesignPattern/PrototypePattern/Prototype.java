package com.NEU.laq.DesignPattern.PrototypePattern;

import java.io.*;

public class Prototype {
    /**
     * 原型模式: 对象通过自我复制生成新的对象, 可以被用来获取对象的当前状态
     * 基本实现:
     *      实现Cloneable接口, 并重写clone方法
     * 注意:
     *      1. Cloneable接口只是一个标志接口, 因此其为空接口
     *      2. 因为Object类中的clone方法返回的是一个Object对象, 又子类重写父类方法时, 返回值类型为原返回类型或其子类,
     *      所以子类中的clone方法返回值可以为任意类型.    -->  Object类是所有类的基类
     * 深拷贝和浅拷贝的区别:
     *      浅拷贝是完全的值复制, 在对对象进行拷贝时, 只是把对象的引用传递了过去, 导致二者的对象成员指向同一片内存区域
     *      深拷贝会在对对象进行拷贝时, 内部的对象也会被复制一份, 此时二者的对象成员虽然数值一样, 但是指向不同的内存区域, 互不影响
     * [注]1. 浅拷贝处理基本类型和String, 深拷贝处理对象类型
     *     2. 为什么String是对象类型, 却可以被浅拷贝处理?
     *           因为String类型的值是唯一的, 修改String类型的值的时候, 其实是新建了一个对象, 并返回新对象的地址,
     *        也就是说改变的是对象成员存储的地址, 而不是改变对象成员所指向空间内部的元素值
     */
    public static void main(String[] args) throws Exception{
        Student stu = new Student("tom",23);
        School school = new School("Centre school",stu);
        // 使用原型模型完成对象运行时状态的复刻   --> 让对象进行自我复制, 生成新的对象
        // 使用深拷贝的第一种方式: 重写clone方法
        School school1 = school.clone();
        System.out.println(school + " " + school.hashCode());
        System.out.println(school1 + " " + school1.hashCode());
        school1.getStu().setName("jerry");
        System.out.println(school + " " + school.hashCode());
        System.out.println(school1 + " " + school1.hashCode());
        System.out.println("-----------------------------");
        // 使用深拷贝的第二种方式: 序列化和反序列化
        School school2 = school.deepClone();
        System.out.println(school + " " + school.hashCode());
        System.out.println(school2 + " " + school2.hashCode());
        school2.getStu().setName("micheal");
        System.out.println(school + " " + school.hashCode());
        System.out.println(school2 + " " + school2.hashCode());
    }
}

class Student implements  Cloneable, Serializable {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student)super.clone();
    }
}

class School implements Cloneable, Serializable{
    private String name;
    private Student stu;

    public School(String name, Student stu) {
        this.name = name;
        this.stu = stu;
    }

    public String getName() {
        return name;
    }

    public Student getStu() {
        return stu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", stu=" + stu +
                '}';
    }

//
    // 浅拷贝
//    @Override
//    protected School clone() throws CloneNotSupportedException {
//        return (School)super.clone();
//    }


    // 深拷贝方式一: 重写clone方法
    // 这种实现方式在成员对象较多且对象依赖链较长时代码逻辑比较复杂
    @Override
    public School clone() throws CloneNotSupportedException {
        // 处理基本类型和String类型的拷贝
        School res = (School)super.clone();
        // 处理对象类型的拷贝
        //res.stu = stu.clone();
        res.setStu(stu.clone());
        return res;
    }

    // 深拷贝方式二: 序列化和反序列化
    // 使用第二种方式完成深拷贝的时候, 可以不用实现Cloneable接口
    // 若要对以前定义的没实现Cloneable接口的类进行深拷贝, 则只可以使用第二种方式实现
    public School deepClone(){
        School school = null;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)
            ){
            oos.writeObject(this);
            byte[] bytes = bos.toByteArray();
            try(ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis)){
                school = (School)ois.readObject();
                // 强转不会产生新对象
//                school = (School)obj;
//                System.out.println(obj == school);
//                System.out.println(obj.hashCode() + " , " + school.hashCode());

            }

        }catch(Exception e){
            e.getMessage();
        }
        return school;
    }
}

