package com.NEU.laq.otherKnow;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyReflect {
    public static void main(String[] args) {
        Animal animal = new Animal("tiger", 3);
        Class classType = animal.getClass();
        System.out.println(classType.getName());
        Constructor[] constructors = classType.getConstructors();
        Field[] fields = classType.getDeclaredFields();
//        for(int i = 0; i < constructors.length; i++){
//            System.out.print(Modifier.toString(constructors[i].getModifiers()) + "\t");
//            Class[] parameterTypes = constructors[i].getParameterTypes();
//            for(Class tmp : parameterTypes)
//                System.out.print(tmp.getName() + "\t");
//            System.out.println();
//        }
        try {
            Constructor constructor = classType.getDeclaredConstructor(new Class[]{String.class,int.class});
            //constructor.setAccessible(true);
            Object obj = constructor.newInstance("Cat",4);
            for(Field filed : fields){
                filed.setAccessible(true);
                System.out.println(filed.getName() + ": " + filed.get(animal) + "\t" + filed.getName() + ":" + filed.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method[] methods = classType.getDeclaredMethods();
        for(Method method : methods){
            System.out.print(Modifier.toString(method.getModifiers()) + "\t" + "方法名称: " + method.getName() + "\t");
            System.out.print("返回值类型: " + method.getReturnType() + "\t" + "参数类型: ");
            Class[] parameterClasses = method.getParameterTypes();
            for(Class parameterClass : parameterClasses)
                System.out.print(parameterClass.getName() + "\t");
            System.out.println();
        }
        try {
            Method method = classType.getDeclaredMethod("print",new Class[]{String.class,int.class});
            //method.invoke(Animal.class, "类的静态方法用类对象调用", 23);
            method.invoke(Animal.class, new Object[]{"类的静态方法用类对象调用", 23});
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 类名规范: 包名 + 类名
        String className = "java.lang.Integer";
        try {
            Class newClass = Class.forName(className);
            System.out.println(newClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class Animal implements Serializable {
    private String name = "Pig";
    private int age = 1;
    private SuperClass animal;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal(int age) {
        this.age = age;
    }

    public Animal(String name, int age) {
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

    public static void print(String content){
        System.out.println(content);
    }

    public static void print(String content,int num){
        System.out.println(content + "\t" + num);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", animal=" + animal +
                '}';
    }

    public void printName(String ... names){

    }
}