package com.tgy.activemq;

/**
 * 以上程序输出内容是？
 *
 * (a) true
 * (b) false
 * (c) 编译错误
 * (d) 以上答案都不对
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-18 13:55
 **/

public class LazyTest {
    private static boolean initialized = false;

    static {
        //static 模块是通过main 线程运行的
        println("static 模块执行");
        //子线程
        //Thread t = new Thread(() -> initialized = true);
        /**变态版*/
        Thread t = new Thread(
                //case1:匿名内部类(不能依赖外层类的初始化)
              /*  new Runnable() {
                    @Override
                    public void run() {
                        initialized =true;
                        System.out.printf("线程[%s] - %s\n",Thread.currentThread().getName(),"run 方法执行");
                    }
                }*/
                //case2: Lambda表达式：invokedynamic指令实现（不能运行）
                //() -> {}//invokedynamic指令作为LazyTest 字节码的一部分，需要等待LazyTest类加载完成
                //case3: 方法引用 invokedynamic指令实现（能够运行）
                // System.out ::println 方法属于System.out 对象类java.io.PrintStream,它被Bootstrap Classloader加载 早于LazyTest.class(App/System Classloader)
                System.out ::println
                //加载的外部类和当前类在类初始化阶段不能有相互依赖了，否者容易相互等待
                //Helloworld :helloworld
                );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        //主线程 main 5
        println("main 方法执行\n");
        System.out.println(initialized);
    }
    private static void println(Object object){
        System.out.printf("线程[%s] - %s",Thread.currentThread().getName(),object);
    }

    /**
     原题：
     public class Lazy {

         private static boolean initialized = false;

         static {
             Thread t = new Thread(() -> initialized = true);
             t.start();
             try {
                    t.join();
             } catch (InterruptedException e) {
                    throw new AssertionError(e);
             }
         }

         public static void main(String[] args) {
             System.out.println(initialized);
         }
     }
     以上程序输出内容是？

     (a) true
     (b) false
     (c) 编译错误
     (d) 以上答案都不对
     * */
}