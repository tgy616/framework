package com.tgy.speedkillsystem;

/**
 *  以上程序输出内容是？
 *
 * (a) 运行时异常
 * (b) -1 null 1
 * (c) -1 0 1
 * (d) 编译错误
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-29 13:58
 **/

public class Parsing {
    /**
     * Returns Integer corresponding to s, or null if s is null.
     * @throws NumberFormatException if s is nonnull and
     * doesn't represent a valid integer
     */
    public static Integer parseInt(String s) {
        return (s == null) ?
                (Integer) null : Integer.parseInt(s);
    }
    public static void main(String[] args) {
        System.out.println(parseInt("-1") + " " +
                parseInt(null) + " " +
                parseInt("1"));
    }
}
