package com.wangzaiplus.test;

import java.lang.reflect.Field;

/**
 * 为什么Java中1000==1000为false而100==100为true？
 * @author DragonSwimDiving
 * @program test
 * @Date 2020-08-31 14:39
 **/

public class CommonOfenTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /*Integer a=100,b=100;
        Integer c=1000,d=1000;
        System.out.println(a==b);
        System.out.println(c==d);
        int a1=100,b1=100;
        int c1=1000,d1=1000;
        System.out.println(a1==b1);
        System.out.println(c1==d1);*/
        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = cache.getDeclaredField("cache"); //2
        myCache.setAccessible(true);//3

        Integer[] newCache = (Integer[]) myCache.get(cache); //4
        newCache[132] = newCache[133]; //5

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }
}
