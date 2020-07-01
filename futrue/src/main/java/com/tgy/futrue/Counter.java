package com.tgy.futrue;

/**
 * @author DragonSwimDiving
 * @program futrue
 * @Date 2020-06-29 10:26
 **/

public class Counter {
    /**
     * volatile修饰的计数器
     */
    private volatile static int count = 0;

    public void addCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
