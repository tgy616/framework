package com.tgy.javadelqueue;

import java.lang.reflect.Field;

/**
 * 每日一问之IntegerChange
 *以上程序输出内容是？
 *
 * (a) 100 200 300
 * (b) 300 300 300
 * (c) 100 300 300
 * (d) 以上答案都不是
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-14 15:26
 **/

public class EverydayRequestIntegerChange {
    public static void main(String[] args) throws Exception {
        Integer a = Integer.parseInt("10");
        Integer b = Integer.valueOf(10);
        Integer c = 10;
        changeValue(a, 100);
        changeValue(b, 200);
        changeValue(c, 300);
        System.out.printf("%d %d %d", a, b, c);
    }

    private static void changeValue(Integer i, int value) throws Exception {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(i, value);
    }
}