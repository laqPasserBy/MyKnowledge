package com.NEU.laq.otherKnow;

import java.io.*;

public class MySerializable {
    public static void main(String[] args) throws Exception {
        /**
         * 序列化的实际意义: 保存对象的瞬时状态(对象的实例变量)  --> 对象状态的保存与重建
         * 序列化的是实现方式有两种: 实现Serializable接口 或者 实现 Externalizable接口
         * 实现Serializable接口将会采用默认的序列化方式, 会将此对象以及该对象中的引用全部序列化
         * 可以使用transient来修饰变量, 使变量不会被序列化
         * [注] 1.transient只可用于修饰变量
         *      2.静态变量(static)不会被序列化
         *      3.String, Enum, 数组, 包装类都实现了序列化
         * Externalizable是Serializable的子接口, 实现此接口实现自定义序列化, 通过重写 readExternal 和w riteExternal 方法
         */
//        Person person = new Person("LiMing", 23);
//        // 对象序列化
//        File file = new File("Person.out");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(person);
//        oos.close();
//        //对象反序列化
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        Object newPerson = ois.readObject();
//        ois.close();
//        System.out.println(person);
//        System.out.println(newPerson);
//        System.out.println(person.hashCode());
//        System.out.println(newPerson.hashCode());


//        Student student = new Student("LiMing", 23);
//        // 对象序列化
//        File file = new File("Student.out");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(student);
//        oos.close();
//        //对象反序列化
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        Object newStudent = ois.readObject();
//        ois.close();
//        System.out.println(student);
//        System.out.println(newStudent);
//        System.out.println(newStudent == student);


//        SingletonTeacher singletonTeacher = SingletonTeacher.getInstance();
//        // 对象序列化
//        File file = new File("singletonTeacher.out");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(singletonTeacher);
//        oos.close();
//        //对象反序列化
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        Object newSingletonTeacher = ois.readObject();
//        ois.close();
//        System.out.println(singletonTeacher);
//        System.out.println(newSingletonTeacher);
//        System.out.println(singletonTeacher.hashCode());
//        System.out.println(newSingletonTeacher.hashCode());
        Animal animal = new Animal("LiMing", 23);
        // 对象序列化
        File file = new File("Person.out");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(animal);
        oos.close();
        //对象反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object newAnimal = ois.readObject();
        ois.close();
        System.out.println(animal);
        System.out.println(newAnimal);
        System.out.println(animal.hashCode());
        System.out.println(newAnimal.hashCode());



    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    transient private int age;

    public Person() {
        System.out.println("调用了空参构造器");
    }

    public Person(String name, int age) {
        System.out.println("调用了有参构造器");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.defaultWriteObject();
        oos.writeInt(50);
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        age = ois.readInt();
    }
}

class Student implements Externalizable{
    private static final long serialVersionUID = 4589461452L;
    private String name;
    private int age;

    /**
     * 实现Externalizable的接口需要手动编写 writeExternal 和 readExternal方法, 并在其中完成对象序列化和反序列化
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
        age = in.readInt();
    }

    public Student() {
        System.out.println("调用了空参构造器");
    }

    public Student(String name, int age) {
        System.out.println("调用了有参构造器");
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class SingletonTeacher implements Serializable{
    private static final long serialVersionUID = 564361564564L;
    private String name;
    private int age;

    private SingletonTeacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private static class InnerClassTeacher{
        private static final SingletonTeacher INSTANCE = new SingletonTeacher("ZhaoSi", 25);
    }


    public static SingletonTeacher getInstance(){
        return InnerClassTeacher.INSTANCE;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SingletonTeacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 使用readResolve来对最后生成的对象进行覆盖, 被覆盖的对象因为没有引用指向, 将会被JVM回收
    public Object readResolve(){
        return InnerClassTeacher.INSTANCE;
    }
}
