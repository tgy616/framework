package com.tgy.javadelqueue;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 每日一问之 MyMap
 *以上程序输出内容是？
 *
 * (a) Hello 1 null 3
 * (b) World 1 null 2
 * (c) Hello 2 null 2
 * (d) 以上答案都不是
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-13 13:46
 **/

public class EverydayRequestMyMap {
    public static void main(String[] args) {
        Map map = new IdentityHashMap<>();
        map.put(1, "Hello");
        map.putIfAbsent(1, "World");
        print(map.get(1));
        print(map.size());

        map.put(1024, "A");
        map.putIfAbsent(1024, "B");
        print(map.get(1024));
        print(map.size());

    }
    private static void print(Object object) {
        System.out.print(object + " ");
    }
}
