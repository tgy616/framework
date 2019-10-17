package com.tgy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CocurrentHashMap在JAVA8中存在一个bug，会进入死循环，
 * 原因是递归创建ConcurrentHashMap 对象，但是在1.9已经修复了,场景重现如下
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-09-19 15:08
 **/

public class ConcurrentHashMapDemo {
    private Map<Integer,Integer> cache =new ConcurrentHashMap<>(15);

    public static void main(String[]args){
        ConcurrentHashMapDemo ch =    new ConcurrentHashMapDemo();
        System.out.println(ch.fibonaacci(80));
    }

    public int fibonaacci(Integer i){
        if(i==0||i ==1) {
            return i;
        }

        return cache.computeIfAbsent(i,(key) -> {
            System.out.println("fibonaacci : "+key);
            return fibonaacci(key -1)+fibonaacci(key - 2);
        });

    }
}
