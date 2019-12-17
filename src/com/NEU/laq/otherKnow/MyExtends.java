package com.NEU.laq.otherKnow;

public class MyExtends {
    public static void main(String[] args) {
        SuperClass sup = new SuperClass();
        sup.print();
        sup.print("A");
        sup.print("A","B");
        sup.print("A", "B", "C");
        sup.print(new String[]{"D", "E"});
    }
}


/**
 * 如果方法调用同时符合带有变长参数的方法, 以及固定参数(不带有变长参数)的方法, 则会优先调用固定参数的方法
 */
class SuperClass{
    private String name  = "11";
    public SuperClass(){

    }
    public SuperClass getInstance(){
        return new SuperClass();
    }

    public void print(String ... names){
        System.out.println("变长参数方法被调用");
        for(String str : names){
            System.out.println(str);
        }
    }

    public void print(String name){
        System.out.println("固定参数方法被调用");
        System.out.println(name);
    }
    public void print(String name1, String name2){
        System.out.println("固定参数方法被调用");
        System.out.println(name1 + " , " + name2);
    }
    public void print(){
        System.out.println("固定参数方法被调用");
    }
}



class SubClass extends SuperClass{
    public SubClass(){

    }

    /**
     * 子类重写父类方法时:
     * 1）重写方法不能缩小访问权限；
     * 2）参数列表必须与被重写方法相同；
     * 3）返回类型必须与被重写方法的相同或是其子类；
     * 4）重写方法不能抛出新的异常，或者超过了父类范围的异常，但是可以抛出更少、更有限的异常，或者不抛出异常。
     */
    @Override
    public SubClass getInstance() {
        return new SubClass();
    }
}
