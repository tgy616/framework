package com.tgy;

import java.util.concurrent.CountDownLatch;

/**
 * 死锁的demo
 * Java 程序通过强制循环等待来创建死锁。
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-09-09 15:25
 **/
public class DeadLockDemo {
    //测试nginx 使用20个并发，测试购买商品使用200个并发
    private static final int amount = 200;
    //发令枪，目的是模拟真正的并发，等所有线程都准备好一起请求
    private static CountDownLatch countDownLatch = new CountDownLatch(amount);

    /*
     * 此方法请求两个锁,第一个字符串,然后整数
     */
    public static void method1() {
        synchronized (String.class) {
            System.out.println("Aquired lock on String.class object");

            synchronized (Integer.class) {
                System.out.println("Aquired lock on Integer.class object");
            }
        }
    }

    /*
     * 此方法也请求相同的两个锁,但完全
     * 相反的顺序,即首先整数,然后字符串。
     * 如果一个线程持有字符串锁,则这会产生潜在的死锁
     * 和其他持有整数锁,他们等待对方,永远。
     */
    public static void method2() {
        synchronized (Integer.class) {
            System.out.println("Aquired lock on Integer.class object");

            synchronized (String.class) {
                System.out.println("Aquired lock on String.class object");
            }
        }
    }

    public static void contextLoads() throws InterruptedException {
        for (int i = 0; i < amount; i++) {
            new Thread(new Request()).start();
            countDownLatch.countDown();
        }
        Thread.currentThread().sleep(100000);
    }

    public static void main(String[] args) {
        try {
            contextLoads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Request implements Runnable{
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            method1();
            method2();
        }
    }
}