package com.tgy.javadelqueue.util;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * UUID的生成
 *
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-14 16:58
 **/

public class UUIDTest {
    private static final int THREAD_NUM=1000;
    private static CountDownLatch cdl=new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<1000;i++){
            new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        cdl.await();//等待，等待一个命令
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String randomUUID=getUUID();
                    System.out.println("inser into t_x_id(id) values('"+randomUUID.toString()+"');");
                }
            }).start();
            cdl.countDown();//计数器 每次调用-1 =====0 唤醒所有的线程
        }
        Thread.sleep(2000);
    }

    public static String getUUID(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();

    }
}
