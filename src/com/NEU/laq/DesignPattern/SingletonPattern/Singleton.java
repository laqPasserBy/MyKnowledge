package com.NEU.laq.DesignPattern.SingletonPattern;

public class Singleton {
    public static void main(String[] args) {
        /**
         * 单例模式一共有五种实现方式:
         *      饿汉式; 懒汉式; 双重检查; 静态内部类; 枚举
         * 对应着8种代码实现:饿汉式(2种); 懒汉式(3种);其余各一种
         * 推荐使用: 饿汉式; 双重检查; 静态内部类; 枚举
         * Runtime类的实现就是单例模式饿汉式的一个样例
         */
        System.out.println(compare(SingletonTest1.getInstance(),SingletonTest1.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest2.getInstance(),SingletonTest2.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest3.getInstance(),SingletonTest3.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest4.getInstance(),SingletonTest4.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest5.getInstance(),SingletonTest5.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest6.getInstance(),SingletonTest6.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest7.getInstance(),SingletonTest7.getInstance(),((t1, t2) -> t1 == t2) ));
        System.out.println(compare(SingletonTest8.getInstance(),SingletonTest8.getInstance(),((t1, t2) -> t1 == t2) ));

    }
    public static <T> boolean compare(T t1, T t2, TempFunction tmp){
        return tmp.compare(t1, t2);
    }
}

interface TempFunction<T>{
    boolean compare(T t1, T t2);
}
class SingletonTest1{
    // 私有化构造器
    private SingletonTest1(){

    }
    // 饿汉式1: 用静态成员变量(final 是为了提高效率)实现单例模式
    // 没有实现懒加载(lazy loading)
    private static final SingletonTest1 INSTANCE = new SingletonTest1();

    // 对外提供一个公共接口,返回唯一实例
    public static SingletonTest1 getInstance(){
        return INSTANCE;
    }
}


class SingletonTest2{
    // 私有化构造器
    private SingletonTest2(){

    }
    // 饿汉式2: 用静态成员变量 + 静态代码块 实现单例模式
    // 没有实现懒加载(lazy loading)
    private static SingletonTest2 INSTANCE;
    static {
        INSTANCE = new SingletonTest2();
    }

    // 对外提供一个公共接口,返回唯一实例
    public static SingletonTest2 getInstance(){
        return INSTANCE;
    }
}

class SingletonTest3{
    // 私有化构造器
    private SingletonTest3(){

    }
    private static SingletonTest3 instance;

    //对外提供公共接口获取唯一实例
    public static SingletonTest3 getInstance(){
        // 懒汉式1:
        // 实现了懒加载, 但有线程安全问题
        if(instance != null){
            instance = new SingletonTest3();
        }
        return instance;
    }
}

class SingletonTest4{
    // 私有化构造器
    private SingletonTest4(){

    }
    private static SingletonTest4 instance;

    //对外提供公共接口获取唯一实例
    public static synchronized SingletonTest4 getInstance(){
        // 懒汉式2:
        // 实现了懒加载, 并且解决了线程安全问题, 但是效率较低
        if(instance != null){
            instance = new SingletonTest4();
        }
        return instance;
    }
}

class SingletonTest5{
    // 私有化构造器
    private SingletonTest5(){

    }
    private static SingletonTest5 instance;

    //对外提供公共接口获取唯一实例
    public static  SingletonTest5 getInstance(){
        // 懒汉式3:
        // 实现了懒加载, 并且并未解决线程安全问题, 同时效率较低, 是一种错误的使用方式
        if(instance != null){
            synchronized (SingletonTest5.class) {
                instance = new SingletonTest5();
            }
        }
        return instance;
    }
}

class SingletonTest6{
    // 私有化构造器
    private SingletonTest6(){

    }

    private static volatile SingletonTest6 instance;

    // 对外提供公共接口, 获取唯一实例
    public static SingletonTest6 getInstance(){
        // 使用双重检查(double - check)实现单例模式
        // 实现了懒加载, 并且解决了线程安全问题, 同时效率较高
        if(instance != null){
            synchronized (SingletonTest6.class){
                if(instance != null)
                    instance = new SingletonTest6();
            }
        }
        return instance;
    }
}

class SingletonTest7{
    // 私有化构造器
    private SingletonTest7(){

    }

    // 静态内部类实现单例模式
    // 通过内部类加载机制实现懒加载; 通过类加载机制实现线程安全; 效率高
    // 静态内部类其实已经不再属于内部类的范畴, 它的准确描述应该是: 是一个独立的类, 但是只被外部类使用
    private static class SingletonTest7InnerClass{
        private static final SingletonTest7 INSTANCE = new SingletonTest7();
    }

    // 提供公共接口, 获取唯一实例
    public static SingletonTest7 getInstance(){
        return SingletonTest7InnerClass.INSTANCE;
    }

}

enum SingletonTest8{
    // 使用枚举实现单例模式
    INSTANCE;
    public static SingletonTest8 getInstance(){
        return INSTANCE;
    }
}

