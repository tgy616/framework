package com.tgy.javadelqueue;

import java.util.Random;

/**
 * 每日一问
 *以上程序输出内容是？
 *
 * (a) 运行时异常
 * (b) 3
 * (c) 1.0
 * (d) 以上答案都不是
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-13 10:57
 **/

public class EveryQuestion {
    public static void main(String[] args) {
        Random rnd = new Random();
        boolean toBe = rnd.nextBoolean();
        Number result = (toBe || !toBe) ? new Integer(3) : new Float(1);
        System.out.println(result);
    }
}
