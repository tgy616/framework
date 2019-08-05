package com.tgy;

/**
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-08-01 16:31
 **/
//SOURCE状态（静态）
public class Test {
    public static void main(String[] args) {
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        //sun.misc.Launcher$ExtClassLoader@1540e19d
        ClassLoader c=Test.class.getClassLoader();
        while (c!=null){
            System.out.println(c);
            c=c.getParent();
        }
        System.out.println("test");
    }
}
