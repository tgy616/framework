package com.tgy;

/**
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-08-05 16:36
 **/

public class StringTest {
    public static void main(String[] args) {
        String s="hello";
        String s1=new String("hello");
        System.out.println(s==s1);
        System.out.println(s.equals(s1));
        // intern方法可以将对象变为运行时常量
        // intern是一个native方法
        System.out.println(s==s1.intern());
    }
}
