package com.tgy.activemq;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 快速去重两种方法
 *
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-16 09:55
 **/
public class FastUniqueTest {
    public static void main(String[] args) {
        //方法一
        String[] ss = {"2", "1", "1", "2", "3", "3"};
        Set<String> set = new HashSet<String>(Arrays.asList(ss));
        ss = set.toArray(new String[set.size()]);
        //或者ss= new HashSet<String>(Arrays.asList(ss)).toArray(new String[0]);
        for (String s : ss) {
            System.out.println("方法一:" + s);
        }
        //方法二
        String[] ss1 = {"2", "1", "1", "2", "3", "3"};
        List<String> list = new ArrayList<String>();
        for (String s : ss1) {
            if (!list.contains(s))            //或者list.indexOf(s)!=-1
                list.add(s);
        }
        ss1 = list.toArray(new String[list.size()]);
        for (String s : ss) {
            System.out.println("方法二:" + s);
        }
    }
}
