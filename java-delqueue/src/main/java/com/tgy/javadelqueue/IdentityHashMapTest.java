package com.tgy.javadelqueue;


import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * IdentityHashMap的使用和他的一些特性
 * 比如对于要保存的key，k1和k2，当且仅当k1== k2的时候，IdentityHashMap才会相等，而对于HashMap来说，相等的条件则是：对比两个key的hashCode等
 * IdentityHashMap不是Map的通用实现，它有意违反了Map的常规协定。并且IdentityHashMap允许key和value都为null。
 * 同HashMap，IdentityHashMap也是无序的，并且该类不是线程安全的，如果要使之线程安全，可以调用Collections.synchronizedMap(new IdentityHashMap(…))方法来实现。
 * 注意：
 *
 * IdentityHashMap重写了equals和hashcode方法，不过需要注意的是hashCode方法并不是借助Object的hashCode来实现的，而是通过System.identityHashCode方法来实现的。
 * hashCode的生成是与key和value都有关系的，这就间接保证了key和value这对数据具备了唯一的hash值。同时通过重写equals方法，
 * 判定只有key值全等情况下才会判断key值相等。这就是IdentityHashMap与普通HashMap不同的关键所在。
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-13 16:49
 **/

public class IdentityHashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("a", "2");
        map.put("a", "3");
        System.out.println(map.size()); //1

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(new String("a"), "1");
        hashMap.put(new String("a"), "2");
        hashMap.put(new String("a"), "3");
        System.out.println(hashMap.size()); //1

        Map<Integer, String> hashMap2 = new HashMap<>();
        hashMap2.put(new Integer(200), "1");
        hashMap2.put(new Integer(200), "2");
        hashMap2.put(new Integer(200), "3");
        System.out.println(hashMap2.size()); //1

        Map<Demo, String> hashMap3 = new HashMap<>();
        hashMap3.put(new Demo(1), "1");
        hashMap3.put(new Demo(1), "2");
        hashMap3.put(new Demo(1), "3");
        System.out.println(hashMap3.size()); //3

        System.out.println("=====================================");
        //IdentityHashMap使用===================================
        Map<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put(new String("a"), "1");
        identityHashMap.put(new String("a"), "2");
        identityHashMap.put(new String("a"), "3");
        System.out.println(identityHashMap.size()); //3

        Map<Demo, String> identityHashMap2 = new IdentityHashMap<>();
        identityHashMap2.put(new Demo(1), "1");
        identityHashMap2.put(new Demo(1), "2");
        identityHashMap2.put(new Demo(1), "3");
        System.out.println(identityHashMap2.size()); //3
    }
    private static class Demo {
        private Integer id;

        public Demo(Integer id) {
            this.id = id;
        }
        /**------------------------------------------------------------------*/
        //工具自动生成的equals和hashCode方法
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Demo demo = (Demo) o;
            return Objects.equals(id, demo.id);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id);
        }
        /**------------------------------------------------------------------*/
        //自己手写的equals和hashCode方法
        /* @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public int hashCode() {
            return id;
        }*/
    }

}
