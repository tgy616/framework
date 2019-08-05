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
    }
}
