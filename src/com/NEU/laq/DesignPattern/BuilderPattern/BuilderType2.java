package com.NEU.laq.DesignPattern.BuilderPattern;

public class BuilderType2 {
    /**
     * 一些情况下, 为了简化系统, 可以将指挥者(Director)和抽象构建类(Builder)合并成一个类
     * 此时, 对象各个组件的构建就和 流 的调用相类似, 并且各个组件构建方法名, 可以起的简单直接, 更加接近于注释
     * 如果constructXxx方法过于复杂, 还是建议将二者分开, 因为这么做一定程度上违反了"单一职责原则"
     */
    public static void main(String[] args) {
        Computer.ComputerBuilder computerBuilder = new Computer.ComputerBuilder();
        // 体现了逐步创建
        // Director
        Computer computer =  computerBuilder.cpu("cpu")
                .screen("screen")
                .memory("memory")
                .constructComputer();
        System.out.println(computer);
    }

}




// Product
class Computer{
    private String cpu;
    private String screen;
    private String memory;


    private Computer(ComputerBuilder computerBuilder) {
        setCpu(computerBuilder.cpu);
        setScreen(computerBuilder.screen);
        setMemory(computerBuilder.memory);
    }

    // ConcreteBuilder
    public static final class ComputerBuilder{
        private String cpu;
        private String screen;
        private String memory;
        public  ComputerBuilder cpu(String val){
            cpu = val;
            return this;
        }
        public  ComputerBuilder screen(String val){
            screen = val;
            return this;
        }
        public  ComputerBuilder memory(String val){
            memory = val;
            return this;
        }
        public Computer constructComputer(){
            return new Computer(this);
        }

    }
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", screen='" + screen + '\'' +
                ", memory='" + memory + '\'' +
                '}';
    }
}
