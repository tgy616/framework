package com.tgy.activemq;

/**
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-16 17:25
 **/

public class LambdaTest {
    public static void main(String[] args) {
        // ->  指向的是 Runnable接口
        /**
         * 分析
         * ->这个箭头是lambda表达式的关键操作符
         * ->把表达式分成两截，前面是函数参数，后面是函数体。
         * Thread的构造函数接收的是一个Runnable接口对象，而我们这里的用法相当于是把一个函数当做接口对象传递进去了，这点理解很关键，这正是函数式编程的含义所在。
         * 我们注意到Runnable有个注解 @FunctionalInterface，它是jdk8才引入，它的含义是函数接口。它是lambda表达式的协议注解，
         * 这个注解非常重要，后面做源码分析会专门分析它的官方注释，到时候一目了然。
         */
        new Thread(()->System.out.println("hello world")).start();



        //------------------------Otherthings------------------------------------------------
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out.println("Animals are equal: " + pig == dog);
    }
}
