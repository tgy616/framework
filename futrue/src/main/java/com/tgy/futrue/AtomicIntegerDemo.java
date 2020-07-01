package com.tgy.futrue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger使用方式
 *
 * @author DragonSwimDiving
 * @program futrue
 * @Date 2020-06-29 10:21
 **/

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        Counter counter = new Counter();
        // 100个线程
        for (int i = 0; i < 10; i++) {
            // 每个线程对count累加10次
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.addCount();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("count = " + counter.getCount());
    }

    private static void test2() throws InterruptedException {
        /**
         * 原子性的int
         */
        AtomicInteger count = new AtomicInteger();
        // 100个线程
        for (int i = 0; i < 10; i++) {
            // 每个线程对count累加10次
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count.incrementAndGet();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("count = " + count.get());
    }
}


