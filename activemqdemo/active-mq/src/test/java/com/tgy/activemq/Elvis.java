package com.tgy.activemq;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试每日一问
 * 以上程序输出内容是？
 *
 * (a) Elvis wears size 0 belt.
 * (b) Elvis wears size 73 belt.
 * (c) Elvis wears size -1930 belt.
 * (d) 以上答案都不对
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-15 14:48
 **/

public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private final int beltSize;

    private static final int CURRENT_YEAR =
            Calendar.getInstance().get(Calendar.YEAR);

    private Elvis() {
        beltSize = CURRENT_YEAR - 1930;
    }

    public int beltSize() {
        return beltSize;
    }

    public static void main(String[] args) {
        System.out.println("Elvis wears size " +
                INSTANCE.beltSize() + " belt.");
    }
}
