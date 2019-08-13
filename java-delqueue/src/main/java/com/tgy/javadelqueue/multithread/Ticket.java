package com.tgy.javadelqueue.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * java多线程测试
 *
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-12 14:28
 **/

public class Ticket {
    private static CountDownLatch ctl=new CountDownLatch(1);
    /*初始化库存数量*/
    Integer stock=8;
    //线程安全 synchronized
     public synchronized void reduce(int num){
     //线程不安全
    //public void reduce(int num){
        if ((stock-num)>=0){
            try {
                ctl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock-=num;
            System.out.println(Thread.currentThread().getName()+"成功：卖出"+String.valueOf(num)+"张，库存剩余"+stock+"张");
        }else {
            System.out.println(Thread.currentThread().getName()+"失败：库存不足"+String.valueOf(num)+"张，库存剩余"+stock+"张");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Ticket ticket=new Ticket();
        for (int i=0;i<10;i++){
            new Thread(()->ticket.reduce(1),"用户"+(i+1)).start();
        }
        Thread.sleep(1000l);
        ctl.countDown();
    }

}
