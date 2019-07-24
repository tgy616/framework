package com.tgy.activemq;

/**
 * https://segmentfault.com/n/1330000019845724
 * 以上程序输出内容是？
 *
 * (a) one ten hundred
 * (b) one ten 100
 * (c) 编译错误
 * (d) 以上都不是
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-23 16:15
 **/
public enum PowerOfTen {
    ONE(1), TEN(10),
    HUNDRED(100) {
        @Override
        public String toString() {
            return Integer.toString(val);
        }
    };
    //private final int val;
    public final int val;

    PowerOfTen(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(ONE + " " + TEN + " " + HUNDRED);
    }
}
