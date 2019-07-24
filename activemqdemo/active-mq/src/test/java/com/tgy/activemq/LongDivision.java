package com.tgy.activemq;

/**
 * test
 * 以上程序输出内容是？
 *
 * (a) 5
 * (b) 1000
 * (c) 抛出异常
 * (d) 以上答案都不对
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-16 14:15
 **/

public class LongDivision {
    //******原题**************/
   /* private static final long MILLIS_PER_DAY
            = 24 * 60 * 60 * 1000;

    private static final long MICROS_PER_DAY
            = 24 * 60 * 60 * 1000 * 1000;

    public static void main(String[] args) {
        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }*/
    //******原题**************/

    /**解答*/
    private static final long MILLIS_PER_DAY
            = 24 * 60 * 60 * 1000;

    private static final long MICROS_PER_DAY
            = 24 * 60 * 60 * 1000 * 1000;

    private static final long MICROS_PER_DAY_L
            = 24L * 60 * 60 * 1000 * 1000;

    public static void main(String[] args) {
        System.out.println(MILLIS_PER_DAY);
        //超出int类型 。只取低32位
        System.out.println(MICROS_PER_DAY);
        System.out.println(Integer.MAX_VALUE);
        //模拟只取低32位
        System.out.println(MICROS_PER_DAY_L & Integer.MAX_VALUE);

        System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
    }
}
