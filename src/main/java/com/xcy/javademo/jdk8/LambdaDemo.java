package com.xcy.javademo.jdk8;

public class LambdaDemo {
    //抽象功能接口
    interface Printer {
        void print(String val);
    }
    //通过参数传递功能接口
    public void printSomething(String something, Printer printer) {
        printer.print(something);
    }

    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();
        String something = "I am learning Lambda";
        // 无参数 （）->  System.out.println("anything you wan to print")
        //实现Printer接口（请关注下面这行lambda表达式代码）
/*        Printer printer = (String toPrint)->{System.out.println(toPrint);};
        Printer printer = (String toPrint)->{System.out.println(toPrint);};
        //简化：去掉参数类型
        Printer printer = (toPrint)->{System.out.println(toPrint);};
        //简化：去掉参数括号
        Printer printer = toPrint->{System.out.println(toPrint);};
        //简化：去掉函数体花括号
        Printer printer = toPrint->System.out.println(toPrint);*/
        //调用接口打印
        demo.printSomething(something, toPrint->System.out.println(toPrint));
    }
}
