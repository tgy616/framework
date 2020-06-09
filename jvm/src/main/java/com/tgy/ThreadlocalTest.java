package com.tgy;

import java.util.Random;

/**
 * threadlocal 测试
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2020-05-15 15:17
 **/

public class ThreadlocalTest {

    static ThreadLocal<Integer> threadLocal=new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set(new Random().nextInt(100));
            System.out.println(Thread.currentThread()+"======="+threadLocal.get());
        }).start();
        new Thread(()->{
            threadLocal.set(new Random().nextInt(100));
            System.out.println(Thread.currentThread()+"======="+threadLocal.get());
        }).start();
    }
}
